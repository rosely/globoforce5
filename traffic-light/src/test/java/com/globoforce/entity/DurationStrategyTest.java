package com.globoforce.entity;

import static com.globoforce.entity.DurationStrategy.getCalendar;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;


public class DurationStrategyTest {
	
	private DurationStrategy strategy = new DurationStrategy(new Time(10,0,0), new Time(22,0,0), Calendar.TUESDAY, 3);
	
	@Test
	public void isStrategyValidTrueTest(){
		Calendar calendar = new GregorianCalendar(2017,0,17,11,0);
		Assert.assertTrue(strategy.isStrategyValid(calendar));
		
	}
	
	@Test
	public void isStrategyValidFalseTestDifferentDayOfWeek(){
		Calendar calendar = new GregorianCalendar(2017,01,16,11,0,0);
		Assert.assertFalse(strategy.isStrategyValid(calendar));
		
	}
	
	@Test
	public void isStrategyValidFalseTestDifferentTimeWeek(){
		Calendar calendar = new GregorianCalendar(2017,01,17,9,59,0);
		Assert.assertFalse(strategy.isStrategyValid(calendar));
		
	}
	
	@Test
	public void getCalendarTest(){
		Time time = new Time(9,59,0);
		Assert.assertTrue(new GregorianCalendar(1970,0,01,9,59,0).equals(getCalendar(time)));
	}

}
