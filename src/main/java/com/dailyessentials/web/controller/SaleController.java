package com.dailyessentials.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.dailyessentials.web.entity.Customer;
import com.dailyessentials.web.entity.Sale;
import com.dailyessentials.web.exception.CustomerNotFoundException;
import com.dailyessentials.web.exception.InvalidSaleException;
import com.dailyessentials.web.exception.SaleNotFoundException;
import com.dailyessentials.web.repository.SaleRepository;

@Controller
public class SaleController {

	// API URL
	String url = "http://localhost:8091/api/sales/";

	@Autowired
	private SaleRepository saleRepo;
	// CRUD operations for sale

	// get one sale
	@RequestMapping(value = "/admin/sales/edit/{id}", method = RequestMethod.GET)
	public String getOneSale(@PathVariable("id") long id, Model model) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			Sale customer = restTemplate.getForObject(url + id, Sale.class);
			model.addAttribute("saleObject", customer);
			return "Sale";
		} catch (SaleNotFoundException ex) {
			model.addAttribute("error", "Sale with " + id + " not found");
			return "Sales";
		}
	}

	// get all sales
	@RequestMapping(value = { "/user/sales", "/admin/sales" }, method = RequestMethod.GET)
	public String getAllSales(Model model) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			Sale[] sales = restTemplate.getForObject(url, Sale[].class);
			model.addAttribute("saleObject", sales);
			model.addAttribute("message", "Found " + sales.length + " sales records.");
			return "Sales";
		} catch (Exception ex) {
			model.addAttribute("error", "An error occurred while loading sales data. Please try again.");
			return "Sales";
		}
	}

	// update sale
	@RequestMapping(value = "/admin/sales/edit", method = RequestMethod.POST)
	public String updateOneSale(Model model, @ModelAttribute("saleObject") Sale sale) {
		long id = sale.getId();
		try {
			RestTemplate restTemplate = new RestTemplate();
			Sale saleOld = restTemplate.getForObject(url + id, Sale.class);
			saleOld.setCustomerName(sale.getCustomerName());
			saleOld.setProductName(sale.getProductName());
			saleOld.setQuantity(sale.getQuantity());
			saleOld.setAddress(sale.getAddress());
			restTemplate.put(url + id, saleOld);
			Sale[] customers = restTemplate.getForObject(url, Sale[].class);
			model.addAttribute("saleObject", customers);
			model.addAttribute("message", "Sale record with id " + id + " successfully edited.");
			return "Sales";
		} catch (SaleNotFoundException ex) {
			model.addAttribute("error", "Sale record with id " + id + " not found!");
			return "Sales";
		} catch (InvalidSaleException ex) {
			model.addAttribute("error", "Sale record with id " + id + " is invalid!");
			return "Sales";
		} catch (Exception ex) {
			model.addAttribute("error", "An error occurred while upadting. Please try again.");
			return "Sales";
		}
	}

	// delete sale
	@RequestMapping(value = "/admin/sales/delete/{id}")
	public String deleteOneSale(@PathVariable("id") long id, Model model) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(url + id);
			Sale[] customers = restTemplate.getForObject(url, Sale[].class);
			model.addAttribute("saleObject", customers);
			model.addAttribute("message", "Sale record with id " + id + " successfully deleted.");
			return "Sales";
		} catch (CustomerNotFoundException ex) {
			model.addAttribute("error", "Sale record with id " + id + " not found!");
			return "Sales";
		} catch (Exception ex) {
			model.addAttribute("error", "An error occurred while deleting. Please try again.");
			return "Sales";
		}

	}

}
