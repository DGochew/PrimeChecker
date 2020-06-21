package com.test.primechecker.service;

import java.math.BigInteger;

public interface PrimeCheckerService {

    /**
     * @param number
     * @return boolean
     */
    boolean isPrime(BigInteger number);

    /**
     * @param number
     * @return BigInteger
     * @throws ArithmeticException
     */
    BigInteger findNextPrime(BigInteger number) throws ArithmeticException;

}
