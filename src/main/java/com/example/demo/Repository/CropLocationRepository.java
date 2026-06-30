package com.example.demo.Repository;

import com.example.demo.Beans.CropLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropLocationRepository extends JpaRepository<CropLocation,Long> {
}
