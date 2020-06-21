package com.test.primechecker.web;

import com.test.primechecker.exception.ErrorResponseHolder;
import com.test.primechecker.service.PrimeCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/primeChecker")
public class PrimeCheckerWebService {

    private static final Logger logger = LoggerFactory.getLogger(PrimeCheckerWebService.class);

    @Autowired
    private PrimeCheckerService primeCheckerService;

    /**
     * @param number
     * @return boolean true/false whether is prime or not
     */
    @GetMapping(value = "/isPrime/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isPrime(@PathVariable BigInteger number) {
        return new ResponseEntity<Boolean>(primeCheckerService.isPrime(number), HttpStatus.OK);
    }

    /**
     * @param number
     * @return BigInteger - next prime number, if passed number is have more than 50 bit length returns 0
     * If passed number is prime, returns this number.
     */
    @GetMapping(value = "/findNextPrime/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BigInteger> findNextPrime(@PathVariable BigInteger number) {
        return new ResponseEntity<BigInteger>(primeCheckerService.findNextPrime(number), HttpStatus.OK);
    }

    /**
     * @param ex
     * @return ResponseEntity<ErrorResponseHolder>
     */
    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<ErrorResponseHolder> handleArithmeticException(ArithmeticException ex) {
        logger.error("Negative values are not allowed. ArithmeticException handler executed");
        logger.error("500 Status Code", ex);
        return new ResponseEntity<ErrorResponseHolder>(new ErrorResponseHolder(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getCanonicalName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

