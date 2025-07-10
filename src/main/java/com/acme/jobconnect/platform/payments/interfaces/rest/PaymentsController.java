package com.acme.jobconnect.platform.payments.interfaces.rest;

import com.acme.jobconnect.platform.payments.domain.model.commands.ConfirmPaymentCommand;
import com.acme.jobconnect.platform.payments.domain.model.commands.DeletePaymentCommand;
import com.acme.jobconnect.platform.payments.domain.model.commands.VerifyPaymentByCustomerCommand;
import com.acme.jobconnect.platform.payments.domain.model.commands.VerifyPaymentByWorkerCommand;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetAllPaymentsQuery;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetPaymentByIdQuery;
import com.acme.jobconnect.platform.payments.domain.model.queries.GetPaymentByOfferIdQuery;
import com.acme.jobconnect.platform.payments.domain.services.PaymentCommandService;
import com.acme.jobconnect.platform.payments.domain.services.PaymentQueryService;
import com.acme.jobconnect.platform.payments.interfaces.rest.resources.CreatePaymentResource;
import com.acme.jobconnect.platform.payments.interfaces.rest.resources.PaymentResource;
import com.acme.jobconnect.platform.payments.interfaces.rest.resources.UpdatePaymentResource;
import com.acme.jobconnect.platform.payments.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.acme.jobconnect.platform.payments.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import com.acme.jobconnect.platform.payments.interfaces.rest.transform.UpdatePaymentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payment Management Endpoints")
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        var command = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        var paymentId = paymentCommandService.handle(command).orElseThrow();
        var payment = paymentQueryService.handle(new GetPaymentByIdQuery(paymentId)).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment);
        return new ResponseEntity<>(paymentResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        var query = new GetAllPaymentsQuery();
        var payments = paymentQueryService.handle(query);
        var paymentResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentResources);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long paymentId) {
        var query = new GetPaymentByIdQuery(paymentId);
        var payment = paymentQueryService.handle(query).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment);
        return ResponseEntity.ok(paymentResource);
    }

    @GetMapping("/offer/{offerId}")
    public ResponseEntity<PaymentResource> getPaymentByOfferId(@PathVariable Long offerId) {
        var query = new GetPaymentByOfferIdQuery(offerId);
        var payment = paymentQueryService.handle(query).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment);
        return ResponseEntity.ok(paymentResource);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentResource> updatePayment(@PathVariable Long paymentId, @RequestBody UpdatePaymentResource resource) {
        var command = UpdatePaymentCommandFromResourceAssembler.toCommandFromResource(paymentId, resource);
        var updatedPaymentId = paymentCommandService.handle(command).orElseThrow();
        var updatedPayment = paymentQueryService.handle(new GetPaymentByIdQuery(updatedPaymentId)).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(updatedPayment);
        return ResponseEntity.ok(paymentResource);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        var command = new DeletePaymentCommand(paymentId);
        paymentCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{paymentId}/verify-worker")
    public ResponseEntity<PaymentResource> verifyPaymentByWorker(@PathVariable Long paymentId) {
        var command = new VerifyPaymentByWorkerCommand(paymentId);
        var updatedPaymentId = paymentCommandService.handle(command).orElseThrow();
        var updatedPayment = paymentQueryService.handle(new GetPaymentByIdQuery(updatedPaymentId)).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(updatedPayment);
        return ResponseEntity.ok(paymentResource);
    }

    @PostMapping("/{paymentId}/verify-customer")
    public ResponseEntity<PaymentResource> verifyPaymentByCustomer(@PathVariable Long paymentId) {
        var command = new VerifyPaymentByCustomerCommand(paymentId);
        var updatedPaymentId = paymentCommandService.handle(command).orElseThrow();
        var updatedPayment = paymentQueryService.handle(new GetPaymentByIdQuery(updatedPaymentId)).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(updatedPayment);
        return ResponseEntity.ok(paymentResource);
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<PaymentResource> confirmPayment(@PathVariable Long paymentId) {
        var command = new ConfirmPaymentCommand(paymentId);
        var updatedPaymentId = paymentCommandService.handle(command).orElseThrow();
        var updatedPayment = paymentQueryService.handle(new GetPaymentByIdQuery(updatedPaymentId)).orElseThrow();
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(updatedPayment);
        return ResponseEntity.ok(paymentResource);
    }
} 