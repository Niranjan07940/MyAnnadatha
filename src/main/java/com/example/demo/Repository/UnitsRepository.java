package com.example.demo.Repository;

import com.example.demo.Beans.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepository extends JpaRepository<Units,Long> {
}
