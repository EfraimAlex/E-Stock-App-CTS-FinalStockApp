package com.finalstockapp.finalstockapp.Controller;

import com.finalstockapp.finalstockapp.Model.Company;
import com.finalstockapp.finalstockapp.Repository.companyInterface;
import com.finalstockapp.finalstockapp.Service.CompanyService;
import com.finalstockapp.finalstockapp.stockDetails.MyProxy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@CrossOrigin()
@RestController
@RequestMapping("/api/v1.0/market")
@RequiredArgsConstructor
public class CompanyController {

    Logger logger= LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private MyProxy proxy;

    @Autowired
    private CompanyService service;

    @Autowired
    private companyInterface repo;
    @Value("${kafka.topic.name}")
	private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/Company/{companyCode}")
//    public Iterable<Company> getCompanyByCompanyCode(@PathVariable Integer companyCode) {
    public Company getCompanyByCompanyCode(@PathVariable Integer companyCode) {
        return service.getCompanyByCompanyCode(companyCode);

    }



    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('User','Admin')")
    public Iterable<Company> getdetails() {
        logger.info("use detatils are fetched from company microservice");
        return service.listAll();
    }

//    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/register")
    public Company savecompany(@RequestBody Company companys) {

        if (repo.existsBycompanyCode(companys.getCompanyCode())) {
            //System.out.println("Company Code already Exists");
            return companys;

        } else {
            repo.save(companys);
            return companys;

        }




    }


    @DeleteMapping("/delete/{companyCode}")
    private String deleteCompany(@PathVariable("companyCode") Integer companyCode) {
        service.deleteByCompanyCode(companyCode);

        return "Data Deleted Successfully";
    }


    @GetMapping("/Stocks/{companyCode}")
    public Iterable<Company>  getStocksByCompanyCode(@PathVariable("companyCode") Integer companyCode) {
    return proxy.getStocksByCompanyCode(companyCode);

    }
//    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updateUsers/{companyCode}")
    public String updateCompanysByCode(@PathVariable("companyCode") Integer companyCode,@RequestBody Company company) {

        Company oldCompany= null;

        Optional<Company> optionalCompany = repo.findByCompanyCode((company.getCompanyCode()));
        if (optionalCompany.isPresent()) {
            oldCompany = optionalCompany.get();
            oldCompany.setCompanyName(company.getCompanyName());
            oldCompany.setCompanyCEO(company.getCompanyCEO());
            oldCompany.setCompanyTurnover(company.getCompanyTurnover());
            oldCompany.setCompanyWebsite(company.getCompanyWebsite());
            oldCompany.setStockExchange(company.getStockExchange());
            repo.save(oldCompany);
            return "Data Updated";
        } else {
            return "Data not Updated";
        }

    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deletee/{companyCode}")
    public String deletekafka(@PathVariable("companyCode") Integer companyCode)
    {   service.deleteByCompanyCode(companyCode);
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topicName);
        kafkaTemplate.send(new GenericMessage(companyCode,headers));
        return "Deleted Successfully";
    }



}



