package com.cashub.cashhubbackend.cashub.controller;

import com.cashub.cashhubbackend.cashub.dto.CustomerRequest;
import com.cashub.cashhubbackend.cashub.dto.CustomerResponse;
import com.cashub.cashhubbackend.cashub.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest request) {
        try {
            String customerId = customerService.createCustomer(request.email());
            return new ResponseEntity<>(customerId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{customerId}/add-payment-method")
    public ResponseEntity<String> addPaymentMethodToCustomer(
            @PathVariable String customerId,
            @RequestBody Map<String, String> request) {
        try {
            String paymentMethodId = request.get("paymentMethodId");
            customerService.addPaymentMethodToCustomer(paymentMethodId, customerId);
            return new ResponseEntity<>("Payment method added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String customerId) {
        try {
            CustomerResponse customer = customerService.retrieveCustomer(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomerEmail(@PathVariable String customerId, @RequestBody Map<String, String> request) {
        try {
            String newEmail = request.get("email");
            customerService.updateCustomerEmail(customerId, newEmail);
            return new ResponseEntity<>("Customer email updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
