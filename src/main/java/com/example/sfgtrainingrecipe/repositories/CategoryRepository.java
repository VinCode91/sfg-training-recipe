package com.example.sfgtrainingrecipe.repositories;

import com.example.sfgtrainingrecipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
