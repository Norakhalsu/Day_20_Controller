package com.example.bank_ex.Controller;


import com.example.bank_ex.API.ApiResponse;
import com.example.bank_ex.Model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CostomerController {
    private final List<Customer> customers = new ArrayList<>();
    private long nextId = 1;


    @GetMapping("/get")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse createCustomer(@RequestBody Customer customer) {
        customer.setId(nextId++);
        customers.add(customer);
        return new ApiResponse("Customer Added Successfully");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable long id, @RequestBody Customer updatedCustomer) {
            customers.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .ifPresent(customer -> {
                        customer.setUsername(updatedCustomer.getUsername());
                        customer.setBalance(updatedCustomer.getBalance());
                    });
            return ResponseEntity.ok(new ApiResponse("Customer updated successfully"));
        }


    @DeleteMapping("/delet/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {
        customers.remove(id);
        return new ApiResponse(" Deleted Customer Successfully");
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Customer> depositMoney(@PathVariable int id, @RequestParam double amount) {
        Optional<Customer> optionalCustomer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setBalance(customer.getBalance() + amount);
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/withdr/mony/{id}")
    public Customer withdrawMoney(@PathVariable int id, @RequestParam double amount) {
        Optional<Customer> optionalCustomer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                return customer;
            } else {
                return customer;
            }
        } else {
            return null;
        }
    }


//        Customer customer;
//        if (customer.getBalance() >= amount) {
//            customer.setBalance(customer.getBalance() - amount);
//        } else {
//            throw new RuntimeException("Insufficient balance");
//        }
//        return customer;
//    }


}
