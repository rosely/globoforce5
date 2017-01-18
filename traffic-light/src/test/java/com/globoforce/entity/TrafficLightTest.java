package com.globoforce.entity;

import org.junit.Assert;
import org.junit.Test;

public class TrafficLightTest{
	
	
	@Test
	public void changeStateFromGreenToOrangeTest(){
		TrafficLight t = new TrafficLight();
		t.setState(StateEnum.GREEN);
		t.changeState();
		Assert.assertEquals("Orange", t.getState().getDescription());
	}
	
	@Test
	public void changeStateFromOrangeToRedTest(){
		TrafficLight t = new TrafficLight();
		t.setState(StateEnum.ORANGE);
		t.changeState();
		Assert.assertEquals("Red", t.getState().getDescription());
	}
	
	@Test
	public void changeStateFromRedToGreenTest(){
		TrafficLight t = new TrafficLight();
		t.setState(StateEnum.RED);
		t.changeState();
		Assert.assertEquals("Green", t.getState().getDescription());
	}
}
