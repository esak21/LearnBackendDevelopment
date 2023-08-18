package org.cogesak.kafka.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Component
public class SalesGenerator {

    private baseSales[] sales;

    public SalesGenerator(baseSales[] sales) {
        this.sales = sales;
    }

    public void mySalesGenerator() {
        String fileLocation = "~/projects/LearnBackendDevelopment/CogDemo/demoProject/salesService/src/main/resources/train.json";

        final ObjectMapper salesmapper ;

        salesmapper = new ObjectMapper();

        try{
            sales = salesmapper.readValue(new File(fileLocation), baseSales[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private int getIndex() {
        Random random = new Random();
        return random.nextInt(3000);
    }

    private baseSales getNextSalesRecord (){
        return sales[getIndex()];
    }

    public baseSales getNextSalesData() {
        mySalesGenerator();
        baseSales salesObject = getNextSalesRecord();
        return salesObject;
    }
}
