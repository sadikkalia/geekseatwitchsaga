package com.geekseat.service;

import com.geekseat.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WitchProtector {

    private List<Integer> fibonacciSum;
    private List<Integer> fibonacciSequece;

    @PostConstruct
    public void init() {
        fibonacciSum = new ArrayList<>();
        fibonacciSum.add(1);
        fibonacciSum.add(2);

        fibonacciSequece = new ArrayList<>();
        fibonacciSequece.add(1);
        fibonacciSequece.add(1);
    }

    public BigDecimal findAverageWitchKilled(List<Person> people) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Person person : people) {
            Integer fibonacciTerm = this.getFibonacciTermOnYear(person.getYearOfBirth());
            sum = sum.add(new BigDecimal(fibonacciTerm));
        }

        return sum.divide(new BigDecimal(people.size()), 2, RoundingMode.HALF_UP);
    }

    private Integer getFibonacciTermOnYear(Integer year) {
        int lastNumber = 1;
        int secondLastNumber = 1;
        int sum = 0;
        int peopleKilledThisYear = 1;


        if (year <= 1){
            return 1;
        }

        for (int i = 2; i <= year; i++) {
            sum = lastNumber + secondLastNumber;
            peopleKilledThisYear = peopleKilledThisYear + lastNumber;
            secondLastNumber = lastNumber;
            lastNumber = sum;
        }

        return peopleKilledThisYear;
    }

}
