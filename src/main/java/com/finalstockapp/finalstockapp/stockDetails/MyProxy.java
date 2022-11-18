package com.finalstockapp.finalstockapp.stockDetails;

import com.finalstockapp.finalstockapp.Model.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="http://localhost:8087/api/v1.0/market", name ="USER-CLIENT")
public interface MyProxy {

    @GetMapping("/stock/get")
    public List<stocks>  getAllStocks();


   @GetMapping("/stock/{companyCode}")
   public Iterable<Company>  getStocksByCompanyCode(@PathVariable("companyCode") Integer companyCode);

//    @DeleteMapping("/delete/{companyCode}")
//    public String deleteStockByCompanyCode(@PathVariable("companyCode") Integer companyCode);





}
