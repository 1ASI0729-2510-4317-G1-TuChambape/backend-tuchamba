package com.acme.jobconnect.platform.offers.interfaces.rest;

import com.acme.jobconnect.platform.offers.domain.model.commands.DeleteOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.commands.UpdateOfferCommand;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByClientIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByStatusAndClientIdQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetAllOffersByStatusQuery;
import com.acme.jobconnect.platform.offers.domain.model.queries.GetOfferByIdQuery;
import com.acme.jobconnect.platform.offers.domain.services.OfferCommandService;
import com.acme.jobconnect.platform.offers.domain.services.OfferQueryService;
import com.acme.jobconnect.platform.offers.interfaces.rest.resources.*;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.AddReviewCommandFromResourceAssembler;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.CreateOfferCommandFromResourceAssembler;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.OfferResourceFromEntityAssembler;
import com.acme.jobconnect.platform.offers.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/offers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Offers", description = "Offer Management Endpoints")
public class OffersController {
    private final OfferCommandService offerCommandService;
    private final OfferQueryService offerQueryService;

    public OffersController(OfferCommandService offerCommandService, OfferQueryService offerQueryService) {
        this.offerCommandService = offerCommandService;
        this.offerQueryService = offerQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new offer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Offer created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<OfferResource> createOffer(@RequestBody CreateOfferResource resource) {
        var createOfferCommand = CreateOfferCommandFromResourceAssembler.toCommandFromResource(resource);
        var offer = offerCommandService.handle(createOfferCommand);
        if (offer.isEmpty()) return ResponseEntity.badRequest().build();
        var offerResource = OfferResourceFromEntityAssembler.toResourceFromEntity(offer.get());
        return new ResponseEntity<>(offerResource, HttpStatus.CREATED);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<OfferResource>> getOffersByClientId(@PathVariable Long clientId) {
        var query = new GetAllOffersByClientIdQuery(clientId);
        var offers = offerQueryService.handle(query);
        var offerResources = offers.stream()
                .map(OfferResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(offerResources);
    }

    @GetMapping("/client/{clientId}/status/{status}")
    public ResponseEntity<List<OfferResource>> getOffersByClientIdAndStatus(@PathVariable Long clientId, @PathVariable String status) {
        var query = new GetAllOffersByStatusAndClientIdQuery(status, clientId);
        var offers = offerQueryService.handle(query);
        var offerResources = offers.stream()
                .map(OfferResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(offerResources);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OfferResource>> getOffersByStatus(@PathVariable String status) {
        var query = new GetAllOffersByStatusQuery(status);
        var offers = offerQueryService.handle(query);
        var offerResources = offers.stream()
                .map(OfferResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(offerResources);
    }

    @GetMapping("/{offerId}")
    @Operation(summary = "Get an offer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer found"),
            @ApiResponse(responseCode = "404", description = "Offer not found")
    })
    public ResponseEntity<OfferResource> getOfferById(@PathVariable Long offerId) {
        var getOfferByIdQuery = new GetOfferByIdQuery(offerId);
        var offer = offerQueryService.handle(getOfferByIdQuery);
        return offer.map(offerEntity -> ResponseEntity.ok(OfferResourceFromEntityAssembler.toResourceFromEntity(offerEntity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{offerId}")
    @Operation(summary = "Update an offer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Offer not found")
    })
    public ResponseEntity<OfferResource> updateOffer(@PathVariable Long offerId, @RequestBody UpdateOfferResource resource) {
        var updateOfferCommand = new UpdateOfferCommand(offerId, resource.title(), resource.description(), resource.status(), resource.category(), resource.location(), resource.budget(), resource.requirements(), resource.deadline(), resource.startAt());
        var updatedOffer = offerCommandService.handle(updateOfferCommand);
        if (updatedOffer.isEmpty()) return ResponseEntity.notFound().build();
        var offerResource = OfferResourceFromEntityAssembler.toResourceFromEntity(updatedOffer.get());
        return ResponseEntity.ok(offerResource);
    }

    @DeleteMapping("/{offerId}")
    @Operation(summary = "Delete an offer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Offer deleted"),
            @ApiResponse(responseCode = "404", description = "Offer not found")
    })
    public ResponseEntity<Void> deleteOffer(@PathVariable Long offerId) {
        var deleteOfferCommand = new DeleteOfferCommand(offerId);
        offerCommandService.handle(deleteOfferCommand);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{offerId}/reviews")
    @Operation(summary = "Add a review to an offer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review added"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Offer not found")
    })
    public ResponseEntity<ReviewResource> addReviewToOffer(@PathVariable Long offerId, @RequestBody AddReviewResource resource) {
        var addReviewCommand = AddReviewCommandFromResourceAssembler.toCommandFromResource(offerId, resource);
        var review = offerCommandService.handle(addReviewCommand);
        if (review.isEmpty()) return ResponseEntity.notFound().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return new ResponseEntity<>(reviewResource, HttpStatus.CREATED);
    }
} 