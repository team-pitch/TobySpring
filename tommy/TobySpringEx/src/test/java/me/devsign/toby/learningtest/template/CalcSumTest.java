package me.devsign.toby.learningtest.template;

import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @BeforeEach
   public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }
    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(this.numFilepath);

        assertThat(sum, Is.is(10));

        assertThat(calculator.calcSum(this.numFilepath), CoreMatchers.is(10));

        assertThat(calculator.calcMultiply(this.numFilepath), CoreMatchers.is(24));
    }
}
