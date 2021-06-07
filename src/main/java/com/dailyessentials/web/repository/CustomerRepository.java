package com.dailyessentials.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailyessentials.web.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}