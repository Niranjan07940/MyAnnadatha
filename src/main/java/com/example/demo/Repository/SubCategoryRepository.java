package com.example.demo.Repository;

import com.example.demo.Beans.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    SubCategory findSubCategoryById(Long itemId);

    List<SubCategory> getSubCategoriesByCategoriesId(Long catId);
}
