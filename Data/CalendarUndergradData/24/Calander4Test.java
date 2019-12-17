package com.company;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class Calander4Test {
    Calander4 obj= new Calander4();

    @Before
    public void initialize()
    {
       obj = new Calander4();
    }
    @After
    public void restVal()
    {
        obj= null;
    }

    @org.junit.Test
    public void weekDay()
    {
        assertEquals(true,(5==obj.weekDay(2,28,2019)));
        assertTrue(1==obj.weekDay(2,28,2010));
        assertEquals(true, 1==obj.weekDay(12,31,2019));
    }
    @org.junit.Test
    public void isLeapYear(){
        assertEquals( false, obj.isLeapYear(2001));
        assertEquals(true, obj.isLeapYear(2020));
        assertEquals(false,obj.isLeapYear(1919));
        assertEquals( true, obj.isLeapYear(800));
        assertEquals( true, obj.isLeapYear(2028));
    }
}
