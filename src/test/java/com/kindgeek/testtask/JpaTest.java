package com.kindgeek.testtask;


import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.service.PersonService;
import com.kindgeek.testtask.service.PositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    PersonService personService;
    @Autowired
    PositionService positionService;

    @Test
    public void testPersonRepository(){
        Position position = positionService.getById(8L);
        Person person = personService.getById(5L);

        Person person1 = new Person();
        person1.setName("Serduk A.V");
        person1.setEmail("rgrhg@gmail.com");
        person1.setPosition(position);
        personService.update(5L,person1);

    }
}
