package com.hendisantika.springbootfilter.controller;

import com.hendisantika.springbootfilter.domain.Customer;
import com.hendisantika.springbootfilter.repo.CustomerRepository;
import com.hendisantika.springbootfilter.web.CustomerByNameSpecification;
import com.hendisantika.springbootfilter.web.NotDeletedCustomerSpecification;
import net.kaczmarzyk.spring.data.jpa.domain.DateBefore;
import net.kaczmarzyk.spring.data.jpa.domain.DateBetween;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-filter
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/01/18
 * Time: 08.23
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepo;

    @RequestMapping
    public Iterable<Customer> listAllCustomers() {
        return customerRepo.findAll();
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new IllegalArgumentException("customer does not exist!"));
        customer.delete();
        customerRepo.save(customer);
    }

    @GetMapping(params = {"firstName"})
    public Iterable<Customer> filterCustomersByFirstName(
            @Spec(path = "firstName", spec = Like.class) NotDeletedCustomerSpecification spec) {

        return customerRepo.findAll(spec);
    }

    @GetMapping(params = {"lastName"}) // gender param is optional
    public Iterable<Customer> filterCustomersByLastNameAndGender(
            @And({@Spec(path = "lastName", spec = Like.class),
                    @Spec(path = "gender", spec = Like.class)}) Specification<Customer> spec) {

        return customerRepo.findAll(spec);
    }

    @GetMapping(params = {"name"})
    public Iterable<Customer> filterCustomersByNameWithPaging(
            CustomerByNameSpecification spec,
            @PageableDefault(size = 1, sort = "id") Pageable pageable) {

        return customerRepo.findAll(spec, pageable);
    }

    @GetMapping(params = {"registeredBefore"})
    public Iterable<Customer> findCustomersRegisteredBefore(
            @Spec(path = "registrationDate", params = "registeredBefore", config = "yyyy-MM-dd", spec = DateBefore.class) NotDeletedCustomerSpecification spec) {

        return customerRepo.findAll(spec);
    }

    @GetMapping(params = {"registeredAfter", "registeredBefore"})
    public Iterable<Customer> findCustomersByRegistrationDate(
            @Spec(path = "registrationDate", params = {"registeredAfter", "registeredBefore"}, config = "yyyy-MM-dd", spec = DateBetween.class) NotDeletedCustomerSpecification spec) {

        return customerRepo.findAll(spec);
    }

    @GetMapping(params = {"gender", "name"})
    public Iterable<Customer> findCustomersByGenderAndName(
            @Spec(path = "gender", spec = Like.class) CustomerByNameSpecification spec) {

        return customerRepo.findAll(spec);
    }

    @GetMapping(value = "", params = {"registeredBefore", "name"})
    public Iterable<Customer> findCustomersByRegistrationDateAndName(
            @Spec(path = "registrationDate", params = "registeredBefore", spec = DateBefore.class) NotDeletedCustomerSpecification registrationDateSpec,
            @Or({@Spec(params = "name", path = "firstName", spec = Like.class),
                    @Spec(params = "name", path = "lastName", spec = Like.class)}) NotDeletedCustomerSpecification nameSpec) {

        Specification<Customer> spec = Specifications.where(registrationDateSpec).and(nameSpec);

        return customerRepo.findAll(spec);
    }
}
