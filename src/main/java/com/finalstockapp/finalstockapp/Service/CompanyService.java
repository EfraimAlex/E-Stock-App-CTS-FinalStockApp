package com.finalstockapp.finalstockapp.Service;

import com.finalstockapp.finalstockapp.Model.Company;
import com.finalstockapp.finalstockapp.Repository.companyInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {


    @Autowired
    private companyInterface repo;




//    public Iterable<Company> getCompanyByCompanyCode(Integer companyCode) {
public Company getCompanyByCompanyCode(Integer companyCode) {
        return this.repo.getCompanyByCompanyCode(companyCode);
    }


    public Iterable<Company> listAll() {
        return this.repo.findAll();
    }



public void deleteByCompanyCode(Integer companyCode) {
    repo.deleteById(companyCode);
}
}