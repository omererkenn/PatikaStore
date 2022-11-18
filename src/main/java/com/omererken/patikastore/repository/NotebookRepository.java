package com.omererken.patikastore.repository;

import com.omererken.patikastore.model.Mobile;
import com.omererken.patikastore.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {
    List<Notebook> findAllByNameContainsIgnoreCase(String name);
}
