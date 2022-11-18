package com.finalstockapp.finalstockapp.Model;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name= "appleData")

@ToString
@Getter
@Setter
public class Company {



//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private Integer id;
    @Id
    @Column(nullable = false, unique = true)
    private Integer companyCode;

    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false )
    private String companyCEO;
   @Column(nullable = false)
    private Integer companyTurnover;
    @Column(nullable = false )
    private String companyWebsite;
   @Column(nullable = false)
    private String stockExchange;

    public Company( ) {

    }

    public Company(  Integer companyCode, String companyName, String companyCEO, Integer companyTurnover, String companyWebsite, String stockExchange) {

       this.companyCode = companyCode;
       this.companyName = companyName;
       this.companyCEO = companyCEO;
       this.companyTurnover = companyTurnover;
       this.companyWebsite = companyWebsite;
       this.stockExchange = stockExchange;
    }

  
}
