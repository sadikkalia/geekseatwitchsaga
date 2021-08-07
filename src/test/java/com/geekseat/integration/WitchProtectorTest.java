package com.geekseat.integration;

import com.geekseat.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WitchProtectorTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnCorrectAverageTest() throws Exception {
        List<Person> people = getPeopleList(10, 12, 13, 17);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:"
                + port + "/witchsaga/expelwitch", people, String.class);

        assertEquals( "4.50", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnMinusOneWhenYearOfDeathIsSmallerThenAgeOfDeathTest() throws Exception {
        List<Person> people = getPeopleList(10, 9, 15, 17);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:"
                + port + "/witchsaga/expelwitch", people, String.class);

        assertEquals( "-1", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }



    @Test
    void shouldReturnMinusOneWhenAgeOfDeathIsNegativeTest() throws Exception {
        List<Person> people = getPeopleList(-9, 10, 14, 20);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:"
                + port + "/witchsaga/expelwitch", people, String.class);

        assertEquals( "-1", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldReturnMinusOneWhenYearOfDeathIsNegativeTest() throws Exception {
        List<Person> people = getPeopleList(10,  -12, 16, 17);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:"
                + port + "/witchsaga/expelwitch", people, String.class);

        assertEquals( "-1", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private List<Person> getPeopleList(int firstPersonAgeOfDeath, int firstPersonYearOfDeath,
                                       int secondPersonAgeOfDeath, int secondPersonYearOfDeath) {
        List<Person> people = new ArrayList<>();
        Person person1 = new Person();
        person1.setAgeOfDeath(firstPersonAgeOfDeath);
        person1.setYearOfDeath(firstPersonYearOfDeath);
        Person person2 = new Person();
        person2.setAgeOfDeath(secondPersonAgeOfDeath);
        person2.setYearOfDeath(secondPersonYearOfDeath);

        people.add(person1);
        people.add(person2);
        return people;
    }

}
