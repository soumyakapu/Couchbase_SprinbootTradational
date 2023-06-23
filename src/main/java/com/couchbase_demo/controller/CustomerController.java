package com.couchbase_demo.controller;

import com.couchbase_demo.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;
import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
//    @Autowired
//    private Repo repo;
        @Autowired
        private CouchbaseTemplate repo;
    @PostMapping("")
    public Customer create(@RequestBody Customer customer){
        return repo.save(customer);
    }
    // using CouchbaseRepository
//    @GetMapping()
//    public List<Customer> getAll(){
//        return repo.findAll();
//    }

//    @PutMapping("/update")
//    public Customer updateById(@RequestBody Customer customer ){
//        Optional<Customer> optionalCustomer = repo.findById(customer.getId());
//        Customer customer1 = optionalCustomer.get();
//        customer1.setName(customer.getName());
//        customer1.setNumber(customer.getNumber());
//
//       return  repo.save(customer1);
//    }
//    @DeleteMapping("/{id}")
//    public String delteted(@PathVariable Integer id){
//        Optional<Customer> optionalCustomer = repo.findById(id);
//        Customer customer1 = optionalCustomer.get();
//        repo.delete(customer1);
//        return "deleted Customer";
//    }
    // using Couchbase Template
    @GetMapping("/get")
    public List<Customer> fetch(){
      return  repo.findByQuery(Customer.class).all();
    }
    @GetMapping("/getById/{id}")
    public Customer getById(@PathVariable int id){
       return  repo.findById(Customer.class).one(id+"");
    }
    @GetMapping("/getByName/{name}")
    public Customer getByName(@PathVariable String name){
        Query query  = new Query().addCriteria(QueryCriteria.where("name").is(name));
      return repo.findByQuery(Customer.class).matching(query).one().get();
    }
    @GetMapping("/gets")
    public List<Customer> gets(){
        Query query = new Query().addCriteria(QueryCriteria.where("number").gte(9_000_000_000L));
        return repo.findByQuery(Customer.class).matching(query).all();
    }

}
