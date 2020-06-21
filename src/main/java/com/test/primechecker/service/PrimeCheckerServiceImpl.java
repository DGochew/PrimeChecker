package com.test.primechecker.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PrimeCheckerServiceImpl implements PrimeCheckerService {

    @Override
    public boolean isPrime(BigInteger number) {
        if (number.bitLength() > 50) {
            return false;
        }
        BigInteger two = new BigInteger("2");
        if (number.equals(BigInteger.ONE) || number.equals(two)) {
            return true;
        }
        if (number.compareTo(BigInteger.ZERO) <= 0 ||
                number.mod(two).equals(BigInteger.ZERO)) { //check if even or less than 0
            return false;
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
