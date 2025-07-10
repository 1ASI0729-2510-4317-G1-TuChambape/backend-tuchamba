package com.acme.jobconnect.platform.users.interfaces.rest;

import com.acme.jobconnect.platform.users.domain.model.commands.DeleteWorkerCommand;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllWorkersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetWorkerByIdQuery;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.domain.service.WorkerQueryService;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CreateWorkerResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UpdateWorkerResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.WorkerResource;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.CreateWorkerCommandFromResourceAssembler;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.UpdateWorkerCommandFromResourceAssembler;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.WorkerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users/workers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workers", description = "Worker Management Endpoints")
@RequiredArgsConstructor
public class WorkersController {

    private final UserCommandService userCommandService;
    private final WorkerQueryService workerQueryService;

    @PostMapping
    public ResponseEntity<Long> createWorkerProfile(@RequestBody CreateWorkerResource resource) {
        var command = CreateWorkerCommandFromResourceAssembler.toCommandFromResource(resource);
        var workerId = userCommandService.handle(command);
        return new ResponseEntity<>(workerId.get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkerResource>> getAllWorkers() {
        var query = new GetAllWorkersQuery();
        var workers = workerQueryService.handle(query);
        var workerResources = workers.stream()
                .map(WorkerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(workerResources);
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<WorkerResource> getWorkerById(@PathVariable Long workerId) {
        var query = new GetWorkerByIdQuery(workerId);
        var worker = workerQueryService.handle(query).orElseThrow();
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(worker);
        return ResponseEntity.ok(workerResource);
    }

    @PutMapping("/{workerId}")
    public ResponseEntity<WorkerResource> updateWorker(@PathVariable Long workerId, @RequestBody UpdateWorkerResource resource) {
        var command = UpdateWorkerCommandFromResourceAssembler.toCommandFromResource(workerId, resource);
        var updatedWorkerId = userCommandService.handle(command).orElseThrow();
        var updatedWorker = workerQueryService.handle(new GetWorkerByIdQuery(updatedWorkerId)).orElseThrow();
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(updatedWorker);
        return ResponseEntity.ok(workerResource);
    }

    @DeleteMapping("/{workerId}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long workerId) {
        var command = new DeleteWorkerCommand(workerId);
        userCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
} 