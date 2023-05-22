package com.saper.first.services;

import org.springframework.stereotype.Component;

@Component
public class PayService {

//    TypeService typeService = new TypeService();
//    TimeService timeService = new TimeService();
    TypeService typeService;
    TimeService timeService;
    public PayService(TypeService typeService, TimeService timeService) {
        this.typeService = typeService;
        this.timeService = timeService;
    }


    public double calcCost(int minutes, String type){
        return typeService.calcType(type) * timeService.calcTime(minutes);
    }
}
