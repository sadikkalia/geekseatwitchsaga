package com.geekseat.controller;

import com.geekseat.model.Person;
import com.geekseat.service.WitchProtector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
public class WitchProtectorController {

    private WitchProtector witchProtector;

    @PostMapping(value = "/expelwitch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal expelWitch(@RequestBody  List<Person> people) {
        if (!validatePeople(people)) return new BigDecimal(-1);

        return witchProtector.findAverageWitchKilled(people);
    }

    private boolean validatePeople(List<Person> people) {
        if (people.size() < 2) return false;

        for (Person person : people) {
            if (person.getYearOfDeath() < 1 || person.getAgeOfDeath() < 1
                    || person.getYearOfDeath() < person.getAgeOfDeath()) return false;
        }

        return true;
    }

    @Autowired
    public void setWitchProtector(WitchProtector witchProtector) {
        this.witchProtector = witchProtector;
    }
}
