package com.finalstockapp.finalstockapp.Repository;

import com.finalstockapp.finalstockapp.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface companyInterface extends JpaRepository<Company,Integer> {

    Optional<Company> findByCompanyCode(Integer companyCode);



    @Query("SELECT ud from appleData ud where ud.companyCode=?1")
//    public List<Company> getCompanyByCompanyCode(Integer companyCode);
    public Company getCompanyByCompanyCode(Integer companyCode);



    boolean existsBycompanyCode(Integer companyCode);
}
