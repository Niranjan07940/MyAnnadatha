package com.example.demo.Repository;

import com.example.demo.Beans.CropDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface CropDetailsRepository extends JpaRepository<CropDetails,Long> {
    @Query("""
        SELECT c,
               (
                   6371 * acos(
                       cos(radians(:latitude))
                       * cos(radians(cl.latitude))
                       * cos(radians(cl.longitude) - radians(:longitude))
                       + sin(radians(:latitude))
                       * sin(radians(cl.latitude))
                   )
               )
        FROM CropDetails c
        JOIN c.cropLocation cl
        WHERE (
                   6371 * acos(
                       cos(radians(:latitude))
                       * cos(radians(cl.latitude))
                       * cos(radians(cl.longitude) - radians(:longitude))
                       + sin(radians(:latitude))
                       * sin(radians(cl.latitude))
                   )
               ) <= :radius
        ORDER BY (
                   6371 * acos(
                       cos(radians(:latitude))
                       * cos(radians(cl.latitude))
                       * cos(radians(cl.longitude) - radians(:longitude))
                       + sin(radians(:latitude))
                       * sin(radians(cl.latitude))
                   )
               )
        """)
    List<Object[]> findNearbyCrops(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("radius") Double radius);

    Page<CropDetails> getCropDetailsBySubCategoryId(int subCategory, Pageable pageable);

    Page<CropDetails> findBySubCategoryCategoriesId(Long categoryId, Pageable pageable);


}
