package com.globoforce.dao;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.globoforce.entity.DurationStrategy;
import com.globoforce.entity.TrafficLight;

public class TrafficLightDao extends BaseDao{
	
	
	
	public List<TrafficLight> findAll(){
		getEntityManager().getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TrafficLight> cq = builder.createQuery(TrafficLight.class);
		Root<TrafficLight> t = cq.from(TrafficLight.class);
		cq.select(t);
		TypedQuery<TrafficLight> q = em.createQuery(cq);
		List<TrafficLight> allTrafficLights = q.getResultList();
		
		return allTrafficLights;
	}
	
	public void addTrafficLight(TrafficLight t){
		getEntityManager().getTransaction().begin();
		em.persist(t);
		commitEntityManager();
		
	}
	
	public void generateData(){
		TrafficLight trafficLight1 = new TrafficLight();
		trafficLight1.addStratetegy(new DurationStrategy(new Time(22, 10, 0),new Time(22, 55, 0),Calendar.MONDAY, 3));
		trafficLight1.addStratetegy(new DurationStrategy(new Time(1, 0, 1),new Time(23, 51, 0),Calendar.WEDNESDAY, 4));
		
		addTrafficLight(trafficLight1);
		
		TrafficLight trafficLight2 = new TrafficLight();
		trafficLight2.addStratetegy(new DurationStrategy(new Time(22, 10, 0),new Time(22, 55, 0),Calendar.TUESDAY, 5));
		trafficLight2.addStratetegy(new DurationStrategy(new Time(2, 0, 0),new Time(23, 0, 0),Calendar.WEDNESDAY, 6));
		
		addTrafficLight(trafficLight2);
		
		TrafficLight trafficLight3 = new TrafficLight();
		trafficLight3.addStratetegy(new DurationStrategy(new Time(3, 0, 0),new Time(23, 59, 59),Calendar.WEDNESDAY, 7));
		trafficLight3.addStratetegy(new DurationStrategy(new Time(3, 0, 0),new Time(23, 51, 0),Calendar.FRIDAY, 8));
		
		addTrafficLight(trafficLight3);
		
	}
	
	

}
