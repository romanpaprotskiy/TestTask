package com.kindgeek.testtask;


import com.kindgeek.testtask.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testPersonRepository(){


    }
}
