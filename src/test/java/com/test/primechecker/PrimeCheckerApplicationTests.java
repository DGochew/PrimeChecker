package com.test.primechecker;

import com.test.primechecker.web.PrimeCheckerWebService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
class PrimeCheckerApplicationTests {

    @Autowired
    private PrimeCheckerWebService primeCheckerWebService;

    @Test
    void contextLoads() {
        assertThat(primeCheckerWebService).isNotNull();
    }

}
