package com.finalstockapp.finalstockapp.stockDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class stocks {

    private Integer id;

    private Float stock;

    private  String companyName;

    private Integer companyCode;

    private LocalDateTime timeStamp;
}
