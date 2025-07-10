package com.acme.jobconnect.platform.users.interfaces.rest;

import com.acme.jobconnect.platform.users.domain.model.commands.DeleteCustomerCommand;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllCustomersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetCustomerByIdQuery;
import com.acme.jobconnect.platform.users.domain.service.CustomerQueryService;
import com.acme.jobconnect.platform.users.domain.service.UserCommandService;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CreateCustomerResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.CustomerResource;
import com.acme.jobconnect.platform.users.interfaces.rest.resources.UpdateCustomerResource;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.CreateCustomerCommandFromResourceAssembler;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.CustomerResourceFromEntityAssembler;
import com.acme.jobconnect.platform.users.interfaces.rest.transform.UpdateCustomerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Customers", description = "Customer Management Endpoints")
@RequiredArgsConstructor
public class CustomersController {

    private final UserCommandService userCommandService;
    private final CustomerQueryService customerQueryService;

    @PostMapping
    public ResponseEntity<Long> createCustomerProfile(@RequestBody CreateCustomerResource resource) {
        var command = CreateCustomerCommandFromResourceAssembler.toCommandFromResource(resource);
        var customerId = userCommandService.handle(command);
        return new ResponseEntity<>(customerId.get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResource>> getAllCustomers() {
        var query = new GetAllCustomersQuery();
        var customers = customerQueryService.handle(query);
        var customerResources = customers.stream()
                .map(CustomerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerResources);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable Long customerId) {
        var query = new GetCustomerByIdQuery(customerId);
        var customer = customerQueryService.handle(query).orElseThrow();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(customer);
        return ResponseEntity.ok(customerResource);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerResource resource) {
        var command = UpdateCustomerCommandFromResourceAssembler.toCommandFromResource(customerId, resource);
        var updatedCustomerId = userCommandService.handle(command).orElseThrow();
        var updatedCustomer = customerQueryService.handle(new GetCustomerByIdQuery(updatedCustomerId)).orElseThrow();
        var customerResource = CustomerResourceFromEntityAssembler.toResourceFromEntity(updatedCustomer);
        return ResponseEntity.ok(customerResource);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        var command = new DeleteCustomerCommand(customerId);
        userCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
} 