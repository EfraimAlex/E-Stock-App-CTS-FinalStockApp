package com.finalstockapp.finalstockapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.*;
@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

        private Integer id;

        private Integer companyCode;

        private String companyName;

        private String companyCEO;

        private Integer companyTurnover;

        private String companyWebsite;

        private String stockExchange;
}


