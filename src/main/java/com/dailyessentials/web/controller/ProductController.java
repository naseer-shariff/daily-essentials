package com.dailyessentials.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.dailyessentials.web.entity.Product;
import com.dailyessentials.web.exception.InvalidProductException;
import com.dailyessentials.web.exception.ProductAlreadyExistsException;
import com.dailyessentials.web.exception.ProductNotFoundException;
import com.dailyessentials.web.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	// CRUD operations for product

	// get one product
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product getOneProduct(@PathVariable("id") long id) {
		return this.productRepo.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException();
		});
	}

	// get all products
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return this.productRepo.findAll();
	}

	// create product
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product addProduct(@RequestBody(required = false) Product productObj) {
		if (productObj == null) {
			throw new InvalidProductException();
		}
		return this.productRepo.save(productObj);
	}

	// update product
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public Product updateOneProduct(@PathVariable("id") long id, @RequestBody(required = false) Product productObj) {
		// find record
		this.productRepo.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException();
		});
		productObj.setId(id);
		return this.productRepo.save(productObj);
	}

	// delete product
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteOneProduct(@PathVariable("id") long id) {

		// verify product exists
		Product fetchedProduct = this.productRepo.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException();
		});

		this.productRepo.delete(fetchedProduct);

	}

}
