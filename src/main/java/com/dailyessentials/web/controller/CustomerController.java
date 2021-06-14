package com.dailyessentials.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dailyessentials.web.entity.Customer;
import com.dailyessentials.web.exception.CustomerAlreadyExistsException;
import com.dailyessentials.web.exception.InvalidCustomerException;
import com.dailyessentials.web.exception.CustomerNotFoundException;
import com.dailyessentials.web.repository.CustomerRepository;

@Controller
public class CustomerController {

	// API URL
	String url = "http://localhost:8091/api/customers/";

	@Autowired
	private CustomerRepository customerRepo;
	// CRUD operations for customer

	// get one customer
	@RequestMapping(value = "/admin/customers/edit/{id}", method = RequestMethod.GET)
	public String getOneCustomer(@PathVariable("id") long id, Model model) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			Customer customer = restTemplate.getForObject(url + id, Customer.class);
			model.addAttribute("customerObject", customer);
			return "Customer";
		} catch (CustomerNotFoundException ex) {
			model.addAttribute("error", "Customer with " + id + " not found");
			return "Customers";
		}
	}

	// get all customers
	@RequestMapping(value = { "/user/customers", "/admin/customers" }, method = RequestMethod.GET)
	public String getAllCustomers(Model model) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			Customer[] customers = restTemplate.getForObject(url, Customer[].class);
			model.addAttribute("customerObject", customers);
			model.addAttribute("message", "Found " + customers.length + " customers.");
			return "Customers";
		} catch (Exception ex) {
			model.addAttribute("error", "An error occurred while loading customers data. Please try again.");
			return "Customers";
		}
	}

	// update customer
	@RequestMapping(value = "/admin/customers/edit", method = RequestMethod.POST)
	public String updateOneCustomer(Model model, @ModelAttribute("customerObject") Customer customer) {
		long id = customer.getId();
		try {
			RestTemplate restTemplate = new RestTemplate();
			Customer customerOld = restTemplate.getForObject(url + id, Customer.class);
			customerOld.setFirstName(customer.getFirstName());
			customerOld.setLastName(customer.getLastName());
			customerOld.setAddress(customer.getAddress());
			restTemplate.put(url + id, customerOld);
			Customer[] customers = restTemplate.getForObject(url, Customer[].class);
			model.addAttribute("customerObject", customers);
			model.addAttribute("message", "Customer with id " + id + " successfully edited.");
			return "Customers";
		} catch (CustomerNotFoundException ex) {
			model.addAttribute("error", "Customer with id " + id + " not found!");
			return "Customers";
		} catch (InvalidCustomerException ex) {
			model.addAttribute("error", "Customer with id " + id + " is invalid!");
			return "Customers";
		} catch (Exception ex) {
			model.addAttribute("error", "An error occurred while upadting. Please try again.");
			return "Customers";
		}
	}

	// delete customer
	@RequestMapping(value = "/admin/customers/delete/{id}")
	public String deleteOneCustomer(@PathVariable("id") long id, Model model) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(url + id);
			Customer[] customers = restTemplate.getForObject(url, Customer[].class);
			model.addAttribute("customerObject", customers);
			model.addAttribute("message", "Customer with id " + id + " successfully deleted.");
			return "Customers";
		} catch (CustomerNotFoundException ex) {
			model.addAttribute("error", "Customer with id " + id + " not found!");
			return "Customers";
		} catch (Exception ex) {
			model.addAttribute("error", "An error occurred while deleting. Please try again.");
			return "Customers";
		}

	}

}
