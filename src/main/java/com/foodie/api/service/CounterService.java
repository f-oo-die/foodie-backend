package com.foodie.api.service;

import com.foodie.api.model.dto.CounterDto;
import com.foodie.api.model.entities.Counter;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
  public static Counter fromPayload(CounterDto payload){
    Counter counter = new Counter();
    counter.setCount(payload.getCount());
    return counter;
  }

  public static CounterDto toPayload(Counter counter){
    CounterDto payload = new CounterDto();
    payload.setId(counter.getId());
    payload.setCount(counter.getCount());
    return payload;
  }
}
