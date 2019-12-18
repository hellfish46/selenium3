package com.automation;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestClass {
    @Test
    public void add(){
        int a = 1;
        int b = 2;
        int res = a + b;

        assertEquals(res, 3);
    }

    @Test
    public void minus(){
        int a = 10;
        int b = 7;
        int res = a - b;

        assertEquals(res, 3);
    }
}
