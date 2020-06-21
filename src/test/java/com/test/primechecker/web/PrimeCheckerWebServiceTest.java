package com.test.primechecker.web;

import com.test.primechecker.service.PrimeCheckerService;
import com.test.primechecker.service.PrimeCheckerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrimeCheckerWebService.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PrimeCheckerWebServiceTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @TestConfiguration
    static class PrimeCheckerServiceImplTestContextConfiguration {
        @Bean
        public PrimeCheckerService primeCheckerService() {
            return new PrimeCheckerServiceImpl();
        }
    }

    @Autowired
    private PrimeCheckerService primeCheckerService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testIsPrimeOkStatus() throws Exception {
        mockMvc.perform(get("/primeChecker/isPrime/{number}", 100))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testIsPrimeResponseType() throws Exception {
        mockMvc.perform(get("/primeChecker/isPrime/{number}", 155))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testIsPrimeReturnsFalse() throws Exception {
        MvcResult result = mockMvc.perform(get("/primeChecker/isPrime/{number}", 100))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("false");
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testIsPrimeReturnsTrue() throws Exception {
        MvcResult result = mockMvc.perform(get("/primeChecker/isPrime/{number}", 101))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("true");
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testFindNextPrimeOkStatus() throws Exception {
        mockMvc.perform(get("/primeChecker/findNextPrime/{number}", 60))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testFindNextPrimeResponseType() throws Exception {
        mockMvc.perform(get("/primeChecker/findNextPrime/{number}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testFindNextPrimeReturns5() throws Exception {
        MvcResult result = mockMvc.perform(get("/primeChecker/findNextPrime/{number}", 4))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("5");
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testFindNextPrimeReturnSameNumberWhenPrime() throws Exception {
        MvcResult result = mockMvc.perform(get("/primeChecker/findNextPrime/{number}", 7))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("7");
    }

    @Test
    @WithUserDetails(value = "admin")
    public void testFindNextPrimeReturnZeroWhenOver50bits() throws Exception {
        MvcResult result = mockMvc.perform(get("/primeChecker/findNextPrime/{number}",
                BigInteger.valueOf(12314124124124124l)))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("0");
    }

}
