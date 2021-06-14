package com.dailyessentials.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailyessentials.web.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}