package com.acme.jobconnect.platform.users.application.internal.queryservices;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllWorkersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetWorkerByIdQuery;
import com.acme.jobconnect.platform.users.domain.service.WorkerQueryService;
import com.acme.jobconnect.platform.users.infrastructure.persistence.jpa.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerQueryServiceImpl implements WorkerQueryService {

    private final WorkerRepository workerRepository;

    @Override
    public Optional<Worker> handle(GetWorkerByIdQuery query) {
        return workerRepository.findById(query.id());
    }

    @Override
    public List<Worker> handle(GetAllWorkersQuery query) {
        return workerRepository.findAll();
    }
} 