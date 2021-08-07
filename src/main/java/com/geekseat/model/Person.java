package com.geekseat.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private Integer ageOfDeath;
    private Integer yearOfDeath;

    public Integer getYearOfBirth() {
        return yearOfDeath - ageOfDeath;
    }
}
