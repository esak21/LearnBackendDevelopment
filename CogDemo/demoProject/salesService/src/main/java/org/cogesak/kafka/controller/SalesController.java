package org.cogesak.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.cogesak.domain.generated.Sales;
import org.cogesak.kafka.Producer.SalesProducer;
import org.cogesak.kafka.datagenerator.SalesGenerator;
import org.cogesak.kafka.datagenerator.baseSales;
import org.cogesak.kafka.dto.salesEvent;
import org.cogesak.kafka.service.SalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/v1/sales")
public class SalesController {

    private SalesGenerator salesGenerator;
    private SalesProducer salesProducer;
    private SalesService salesService;

    public SalesController(SalesGenerator salesGenerator, SalesProducer salesProducer, SalesService salesService) {
        this.salesGenerator = salesGenerator;
        this.salesProducer = salesProducer;
        this.salesService = salesService;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<salesEvent> newOrder (@RequestBody salesEvent salesEventModel){
        log.info("INFO :: Received the Request for the sales data : {} ", salesEventModel);

        for(int i =0; i< 3000; i++) {
            baseSales sales = salesGenerator.getNextSalesData();
            try {
            Sales salesavrodata = salesService.buildSalesAVroData(sales);
            salesProducer.sendSalesAvroProducer(salesavrodata);
            TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(salesEventModel);
    }
}
