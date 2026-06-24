package com.example.demo.Repository;

import com.example.demo.Beans.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Long> {
}
