package com.test.primechecker.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

@Service
public class PrimeCheckerServiceImpl implements PrimeCheckerService {

    private static final int SIEVE_OF_ERATOSTHENES_SIZE = 1000000;

    private boolean[] primeNumbersSieve;

    @PostConstruct
    private void createSieveOfEratosthenes() {

        primeNumbersSieve = new boolean[SIEVE_OF_ERATOSTHENES_SIZE + 1];

        for (int i = 0; i <= SIEVE_OF_ERATOSTHENES_SIZE; ++i) {
            primeNumbersSieve[i] = true;
        }
        primeNumbersSieve[0] = false;
        primeNumbersSieve[1] = false;

        // Mark non-primes <= sieve's limit using Sieve of Eratosthenes
        for (int i = 2; i * i <= SIEVE_OF_ERATOSTHENES_SIZE; ++i) {
            if (primeNumbersSieve[i]) {
                // if i is prime number, then mark multiples of i as non-prime
                for (int j = i; i * j <= SIEVE_OF_ERATOSTHENES_SIZE; j++) {
                    primeNumbersSieve[i * j] = false;
                }
            }
        }
    }


    @Override
    @Cacheable("isPrime")
    public boolean isPrime(BigInteger number) {
        if (number.bitLength() > 50) {
            return false;
        }
        BigInteger two = new BigInteger("2");
        if (number.compareTo(BigInteger.ZERO) <= 0 ||
                (!number.equals(two) && number.mod(two).equals(BigInteger.ZERO))) { //check if even or less than 0
            return false;
        }
        if (BigInteger.valueOf(primeNumbersSieve.length).compareTo(number) > 0) {
            return primeNumbersSieve[number.intValue()];
        }
        BigInteger root = sqrt(number);
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(root) < 1; i = i.add(two)) { //start from 3, check only odd numbers and look for a divisor if any
            if (BigInteger.ZERO.equals(number.mod(i))) { // check if 'i' is divisor of 'number'
                return false;
            }
        }
        return true;
    }

    @Override
    @Cacheable("primes")
    public BigInteger findNextPrime(BigInteger number) throws ArithmeticException {
        if (number.compareTo(BigInteger.valueOf(0)) == -1) {
            throw new ArithmeticException("Negative values are not allowed.");
        } else if (number.bitLength() > 50) {
            return BigInteger.ZERO;
        } else if (isPrime(number)) {
            return number;
        }
        while (!isPrime(number = number.add(BigInteger.ONE))) {
        }
        return number;
    }

    private BigInteger sqrt(BigInteger val) {
        int i = val.bitLength();
        int i1 = val.bitCount();
        BigInteger half = BigInteger.ZERO.setBit(val.bitLength() / 2);
        BigInteger cur = half;
        while (true) {
            BigInteger tmp = half.add(val.divide(half)).shiftRight(1);
            if (tmp.equals(half) || tmp.equals(cur)) {
                return tmp;
            }
            cur = half;
            half = tmp;
        }
    }

}
