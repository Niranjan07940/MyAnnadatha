package com.example.demo.Repository;

import com.example.demo.Beans.CropDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface CropDetailsRepository extends JpaRepository<CropDetails,Long> {
}
