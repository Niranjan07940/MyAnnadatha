package com.example.demo.Repository;

import com.example.demo.Beans.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    SubCategory findSubCategoryById(Long itemId);
}
