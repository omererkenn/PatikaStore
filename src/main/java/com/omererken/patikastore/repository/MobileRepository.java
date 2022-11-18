package com.omererken.patikastore.repository;

import com.omererken.patikastore.model.Category;
import com.omererken.patikastore.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MobileRepository extends JpaRepository<Mobile, Long> {

    List<Mobile> findAllByNameContainsIgnoreCase(String name);
}
