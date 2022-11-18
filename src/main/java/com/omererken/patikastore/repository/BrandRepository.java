package com.omererken.patikastore.repository;

import com.omererken.patikastore.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
