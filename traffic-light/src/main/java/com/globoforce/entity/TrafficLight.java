package com.globoforce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.apache.log4j.Logger;

@Entity
@SequenceGenerator(name = "SEQ_TRAFFIC_LIGHT", sequenceName = "SEQ_TRAFFIC_LIGHT", initialValue = 1)
public class TrafficLight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TRAFFIC_LIGHT")
	@Column(name = "traffic_light_id")
	private Integer id;
	
	private static Logger logger = Logger.getLogger(TrafficLight.class);

	private StateEnum state = StateEnum.GREEN;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="trafficLight")
	private List<DurationStrategy> durationStrategies = new ArrayList<DurationStrategy>();
	
	public void addStratetegy(DurationStrategy durationStrategy){
		this.durationStrategies.add(durationStrategy);
	}
	
	public List<DurationStrategy> getDurationStrategies() {
		return durationStrategies;
	}

	public void changeState(){
		this.state = this.state.changeState();
		logger.info(getState());
	}

	public StateEnum getState() {
		return state;
	}

	protected void setState(StateEnum state) {
		this.state = state;
	}
	
}

enum StateEnum implements StateInterface {
	GREEN("Green"){
		public StateEnum changeState() {
			return StateEnum.ORANGE;
		}
	}, ORANGE("Orange"){
		public StateEnum changeState() {
			return StateEnum.RED;
		}
	}, RED("Red"){
		public StateEnum changeState() {
			return StateEnum.GREEN;
		}
	};
	
	private String description;

	private StateEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

interface StateInterface{
	StateEnum changeState();
}
