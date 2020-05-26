package com.springmicroservice.customerservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    private List<Customer> customers = Arrays.asList(
            new Customer(1, "Rob"),
            new Customer(2, "Rob Rob 2")
    );

    private OrderClient orderClient;

    public CustomerController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/{id}")
    public Customer getCustomerByID(@PathVariable int id) {
            return customers.stream()
                    .filter(customer -> customer.getId() == id)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
    }

    @GetMapping("/{id}/orders")
    public Object getOrdersForCustomer(@PathVariable int id) {
        return orderClient.getOrdersForCustomer(id);
    }
}
