package org.cogesak.kafka.service;

import org.cogesak.domain.generated.Sales;
import org.cogesak.kafka.datagenerator.baseSales;
import org.springframework.stereotype.Component;

@Component
public class SalesService {

    public Sales buildSalesAVroData(baseSales basesales) {
        return Sales.newBuilder()
                .setSalesid(basesales.getSalesId())
                .setTitle(basesales.getTitle())
                .setRating(basesales.getRating())
                .setCategory(basesales.getCategory())
                .setPlatform(basesales.getPlatform())
                .setPrice(basesales.getPrice())
                .setActualprice(basesales.getActualprice())
                .setOffer(basesales.getOffer())
                .setNoratings(basesales.getNorating())
                .setNoreviews(basesales.getNoreviews())
                .setStar5(basesales.getStar5())
                .setStar4(basesales.getStar4())
                .setStar3(basesales.getStar3())
                .setStar2(basesales.getStar2())
                .setStar1(basesales.getStar1())
                .setFulfilled(basesales.getFulfilled())
                .build();
    }
}
