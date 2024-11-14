package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bankManagementSystem")
public class BankController {

    // 1. Create a new list of customers
    ArrayList<Customer> customers = new ArrayList<>();

    // 2. Get all the customers
    @GetMapping("/getCustomers")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    // 3. Add new customer
    @PostMapping("/addCustomer")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("New customer added.");
    }

    // 4. Update a customer
    @PutMapping("/updateCustomer/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index,customer);
        return new ApiResponse("Customer updated.");
    }

    // 5. Delete a customer
    @DeleteMapping("/deleteCustomer/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("Customer deleted.");
    }

    // 6. Deposit money to customer
    @PutMapping("/depositMoney/{amount}/{index}")
    public ApiResponse depositMoney(@PathVariable int amount, @PathVariable int  index) {
        customers.get(index).setBalance(customers.get(index).getBalance() + amount);
        return new ApiResponse(amount + "SR has been deposited to " + customers.get(index).getUserName() + "'s bank account.");
    }

    // 7. Withdraw money from customer
    @PutMapping("/withdrawMoney/{amount}/{index}")
    public ApiResponse withdrawMoney(@PathVariable int amount, @PathVariable int  index) {
        String message = "";
        if(customers.get(index).getBalance() < amount) {
            message = "Insufficient funds.";
        }else {
            customers.get(index).setBalance(customers.get(index).getBalance() - amount);
            message = (amount + "SR has been withdrawn from " + customers.get(index).getUserName() + "'s bank account.");
        }
        return new ApiResponse(message);
    }
}