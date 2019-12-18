package com.automation;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestClass2 {
    @Test
    public void justSout(){
        System.out.println("Test..Test..Test..Test..Test..Test..Test");
        int a = 1;
        assertEquals(a, 1);

    }
}
