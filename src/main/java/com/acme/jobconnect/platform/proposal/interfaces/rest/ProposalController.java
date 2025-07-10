package com.acme.jobconnect.platform.proposal.interfaces.rest;

import com.acme.jobconnect.platform.proposal.domain.model.commands.*;
import com.acme.jobconnect.platform.proposal.domain.model.queries.GetAllProposalByOfferIdQuery;
import com.acme.jobconnect.platform.proposal.domain.model.queries.GetAllProposalByWorkerIdQuery;
import com.acme.jobconnect.platform.proposal.domain.model.queries.GetProposalByIdQuery;
import com.acme.jobconnect.platform.proposal.domain.services.ProposalCommandService;
import com.acme.jobconnect.platform.proposal.domain.services.ProposalQueryService;
import com.acme.jobconnect.platform.proposal.interfaces.rest.resources.CreateProposalResource;
import com.acme.jobconnect.platform.proposal.interfaces.rest.resources.ProposalResource;
import com.acme.jobconnect.platform.proposal.interfaces.rest.resources.UpdateProposalResource;
import com.acme.jobconnect.platform.proposal.interfaces.rest.transform.CreateProposalCommandFromResourceAssembler;
import com.acme.jobconnect.platform.proposal.interfaces.rest.transform.ProposalResourceFromEntityAssembler;
import com.acme.jobconnect.platform.proposal.interfaces.rest.transform.UpdateProposalCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/proposals", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Proposals", description = "Proposal Management Endpoints")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalCommandService proposalCommandService;
    private final ProposalQueryService proposalQueryService;

    @PostMapping
    public ResponseEntity<ProposalResource> createProposal(@RequestBody CreateProposalResource resource) {
        var command = CreateProposalCommandFromResourceAssembler.toCommandFromResource(resource);
        var proposal = proposalCommandService.handle(command).orElseThrow();
        var proposalResource = ProposalResourceFromEntityAssembler.toResourceFromEntity(proposal);
        return new ResponseEntity<>(proposalResource, HttpStatus.CREATED);
    }

    @GetMapping("/{proposalId}")
    public ResponseEntity<ProposalResource> getProposalById(@PathVariable Long proposalId) {
        var query = new GetProposalByIdQuery(proposalId);
        var proposal = proposalQueryService.handle(query).orElseThrow();
        var proposalResource = ProposalResourceFromEntityAssembler.toResourceFromEntity(proposal);
        return ResponseEntity.ok(proposalResource);
    }

    @GetMapping("/offer/{offerId}")
    public ResponseEntity<List<ProposalResource>> getProposalsByOfferId(@PathVariable Long offerId) {
        var query = new GetAllProposalByOfferIdQuery(offerId);
        var proposals = proposalQueryService.handle(query);
        var proposalResources = proposals.stream()
                .map(ProposalResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(proposalResources);
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<ProposalResource>> getProposalsByWorkerId(@PathVariable Long workerId) {
        var query = new GetAllProposalByWorkerIdQuery(workerId);
        var proposals = proposalQueryService.handle(query);
        var proposalResources = proposals.stream()
                .map(ProposalResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(proposalResources);
    }

    @PutMapping("/{proposalId}")
    public ResponseEntity<ProposalResource> updateProposal(@PathVariable Long proposalId, @RequestBody UpdateProposalResource resource) {
        var command = UpdateProposalCommandFromResourceAssembler.toCommandFromResource(proposalId, resource);
        var proposal = proposalCommandService.handle(command).orElseThrow();
        var proposalResource = ProposalResourceFromEntityAssembler.toResourceFromEntity(proposal);
        return ResponseEntity.ok(proposalResource);
    }

    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Void> deleteProposal(@PathVariable Long proposalId) {
        var command = new DeleteProposalByIdCommand(proposalId);
        proposalCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{proposalId}/accept")
    public ResponseEntity<ProposalResource> acceptProposal(@PathVariable Long proposalId) {
        var command = new AcceptProposalCommand(proposalId);
        var proposal = proposalCommandService.handle(command).orElseThrow();
        var proposalResource = ProposalResourceFromEntityAssembler.toResourceFromEntity(proposal);
        return ResponseEntity.ok(proposalResource);
    }

    @PostMapping("/{proposalId}/reject")
    public ResponseEntity<ProposalResource> rejectProposal(@PathVariable Long proposalId) {
        var command = new RejectProposalCommand(proposalId);
        var proposal = proposalCommandService.handle(command).orElseThrow();
        var proposalResource = ProposalResourceFromEntityAssembler.toResourceFromEntity(proposal);
        return ResponseEntity.ok(proposalResource);
    }
}
