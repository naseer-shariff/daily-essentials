package com.dailyessentials.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dailyessentials.web.entity.Sale;
import com.dailyessentials.web.exception.InvalidProductException;
import com.dailyessentials.web.exception.InvalidSaleException;
import com.dailyessentials.web.exception.ProductAlreadyExistsException;
import com.dailyessentials.web.exception.ProductNotFoundException;
import com.dailyessentials.web.exception.SaleNotFoundException;
import com.dailyessentials.web.repository.SaleRepository;

@RestController
public class SaleApiController {
	
	@Autowired
	private SaleRepository saleRepo;
	// CRUD operations for sale

	// get one sale
	@RequestMapping(value = "/api/sales/{id}", method = RequestMethod.GET)
	public Sale getOneSale(@PathVariable("id") long id) {
		return this.saleRepo.findById(id).orElseThrow(() -> {
			throw new SaleNotFoundException();
		});
	}

	// get all sales
	@RequestMapping(value = "/api/sales", method = RequestMethod.GET)
	public List<Sale> getAllSales() {
		return this.saleRepo.findAll();
	}

	// create sale
	@RequestMapping(value = "/api/sales", method = RequestMethod.POST)
	public Sale addSale(@RequestBody(required = false) Sale sale) {
		if (sale == null) {
			throw new InvalidSaleException();
		}
		return this.saleRepo.save(sale);
	}

	// update sale
	@RequestMapping(value = "/api/sales/{id}", method = RequestMethod.PUT)
	public Sale updateOneSale(@PathVariable("id") long id, @RequestBody(required = false) Sale sale) {
		// find record
		this.saleRepo.findById(id).orElseThrow(() -> {
			throw new SaleNotFoundException();
		});
		sale.setId(id);
		return this.saleRepo.save(sale);
	}

	// delete sale
	@RequestMapping(value = "/api/sales/{id}", method = RequestMethod.DELETE)
	public void deleteOneSale(@PathVariable("id") long id) {

		// verify sale exists
		Sale fetchedSale = this.saleRepo.findById(id).orElseThrow(() -> {
			throw new SaleNotFoundException();
		});

		this.saleRepo.delete(fetchedSale);

	}

}
