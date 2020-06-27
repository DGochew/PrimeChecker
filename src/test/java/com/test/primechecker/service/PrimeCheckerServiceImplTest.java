package com.test.primechecker.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrimeCheckerServiceImplTest {

    @TestConfiguration
    static class PrimeCheckerServiceImplTestContextConfiguration {
        @Bean
        public PrimeCheckerService primeCheckerService() {
            return new PrimeCheckerServiceImpl();
        }
    }

    @Autowired
    private PrimeCheckerService primeCheckerService;

    @Test
    public void testIsPrimeReturnsTrue() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(5));
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testIsPrimeReturnsFalse() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(400));
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testIsPrimeCornerCaseValueOne() {
        boolean result = primeCheckerService.isPrime(BigInteger.ONE);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testIsPrimeCornerCaseValueTwo() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(2));
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testIsPrimeCornerCaseValueThree() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(3));
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testIsPrimeCornerCaseValueZero() {
        boolean result = primeCheckerService.isPrime(BigInteger.ZERO);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testIsPrimeCornerCaseNegativeValue() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(-100));
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testIsPrimeCornerCaseOver50BitsReturnFalse() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(4444411231231311213L));
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testIsPrimeWith50bitNumberReturnFalse() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(1022152021565559L));
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testIsPrimeWith50bitNumberReturnsTrue() {
        boolean result = primeCheckerService.isPrime(BigInteger.valueOf(1022152021565567L));
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testFindNextPrimeReturns17() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.valueOf(14));
        assertThat(nextPrime).isEqualTo(BigInteger.valueOf(17));
    }

    @Test
    public void testFindNextPrimeReturnsSameNumber() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.valueOf(17));
        assertThat(nextPrime).isEqualTo(BigInteger.valueOf(17));
    }

    @Test
    public void testFindNextPrimeThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> {
            primeCheckerService.findNextPrime(BigInteger.valueOf(-10));
        });
    }

    @Test
    public void testFindNextPrimeArithmeticExceptionMessage() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            primeCheckerService.findNextPrime(BigInteger.valueOf(-10));
        });

        String expectedMessage = "Negative values are not allowed.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindNextPrimeOver50bitsReturnsZero() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.valueOf(1000000000005559698L));
        assertThat(nextPrime).isEqualTo(BigInteger.ZERO);
    }

    @Test
    public void testFindNextPrimeCornerCaseWithOne() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.ONE);
        assertThat(nextPrime).isEqualTo(BigInteger.valueOf(2));
    }

    @Test
    public void testFindNextPrimeCornerCaseWithTwo() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.valueOf(2));
        assertThat(nextPrime).isEqualTo(BigInteger.valueOf(2));
    }

    @Test
    public void testFindNextPrimeCornerCaseWithThree() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.valueOf(3));
        assertThat(nextPrime).isEqualTo(BigInteger.valueOf(3));
    }

    @Test
    public void testFindNextPrimeWith50bitNumber() {
        BigInteger nextPrime = primeCheckerService.findNextPrime(BigInteger.valueOf(1022152021565559L));
        assertThat(nextPrime).isEqualTo(BigInteger.valueOf(1022152021565567L));
    }

}
