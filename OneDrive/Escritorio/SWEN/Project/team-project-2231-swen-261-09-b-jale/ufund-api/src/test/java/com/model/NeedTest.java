package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.Need;

public class NeedTest {
    @Test
    public void testName() {
        // Setup
        String name = "Flood";
        double cost = 30.00;
        boolean fufilled = false;
        Need need = new Need(name,cost,fufilled);

        String expected_name = "Flood";

        // Invoke
        String actual_name = need.getName();

        // Analyze
        assertEquals(expected_name,actual_name);
    }

    @Test
    public void testToString() {
        // Setup
        String name = "Flood";
        double cost = 30.00;
        boolean fulfilled = false;
        String expected_string = "Name : \"" + name + "\"\nCost : " + cost + "\nFulfilled : " + fulfilled;
        Need need = new Need(name,cost,fulfilled);

        // Invoke
        String actual_string = need.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}
