package com.omererken.patikastore.repository;

import com.omererken.patikastore.model.Brand;
import com.omererken.patikastore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   
}
