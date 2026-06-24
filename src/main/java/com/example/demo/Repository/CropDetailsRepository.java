package com.example.demo.Repository;

import com.example.demo.Beans.CropDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CropDetailsRepository extends JpaRepository<CropDetails,Long> {

}
