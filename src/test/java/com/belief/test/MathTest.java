package com.belief.test;

import junit.framework.TestCase;

public class MathTest extends TestCase {
    protected double fValue1;
    protected double fValue2;

    protected void setUp() {
        fValue1 = 2.0;
        fValue2 = 3.0;
        assertTrue("good bye", false);
    }

    public void testAdd() {
        double result = fValue1 + fValue2;
        assertTrue(result == 5.0);
    }

    public static void main(String[] args) {
        TestCase test = new MathTest() {
            public void runTest() {
                testAdd();
            }
        };
        test.run();
    }
}
