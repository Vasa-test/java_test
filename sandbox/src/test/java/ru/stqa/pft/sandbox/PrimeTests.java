package ru.stqa.pft.sandbox;

import org.junit.Assert;
import org.junit.Test;

public class PrimeTests {

  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }
  @Test
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }
}
