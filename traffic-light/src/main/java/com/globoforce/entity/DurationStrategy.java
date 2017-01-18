package com.globoforce.entity;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_DURATION_STRATEGY", sequenceName = "SEQ_DURATION_STRATEGY", initialValue = 1)
public class DurationStrategy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DURATION_STRATEGY")
	@Column(name = "duration_strategy_id")
	private Integer id;

	private Time start;

	private Time end;

	private int dayOfWeek;

	private Integer seconds;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private TrafficLight trafficLight;
	
	public DurationStrategy() {
	}

	public DurationStrategy(Time start, Time end, int dayOfWeek, Integer seconds) {
		super();
		this.start = start;
		this.end = end;
		this.dayOfWeek = dayOfWeek;
		this.seconds = seconds;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public boolean isStrategyValid(Calendar calendar) {
		if (getDayOfWeek() == calendar.get(Calendar.DAY_OF_WEEK)) {
			Calendar c = new GregorianCalendar();
			c.setTime(new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND)));

			if (c.after(getCalendar(getStart())) && c.before(getCalendar(getEnd())))
				return true;
		}
		return false;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public static Calendar getCalendar(Time time) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(time);

		return calendar;
	}

}
