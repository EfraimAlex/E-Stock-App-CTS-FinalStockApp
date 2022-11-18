package com.finalstockapp.finalstockapp;

import com.finalstockapp.finalstockapp.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestH2Repository extends JpaRepository<Company,Integer> {
   Optional<Company> findByCompanyCode(Integer companyCode);
}
