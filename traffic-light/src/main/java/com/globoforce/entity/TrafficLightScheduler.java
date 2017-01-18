package com.globoforce.entity;

import static com.globoforce.entity.DurationStrategy.getCalendar;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrafficLightScheduler {

	private static final int THREAD_POOL = 1;

	private TrafficLight trafficLight;

	private ScheduledExecutorService scheduledThreadPool;

	public TrafficLightScheduler(TrafficLight trafficLight) {
		super();
		this.trafficLight = trafficLight;
	}

	public void start() {
		schedule(getStrategy(new GregorianCalendar()));
	}

	private void schedule(final DurationStrategy durationStrategy) {
		if (durationStrategy != null) {
			scheduledThreadPool = Executors.newScheduledThreadPool(THREAD_POOL);
			scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

				public void run() {
					trafficLight.changeState();
					if (!durationStrategy.isStrategyValid(new GregorianCalendar())) {
						stop();
					}
				}
			}, 0, durationStrategy.getSeconds(), TimeUnit.SECONDS);
		}
	}

	private DurationStrategy getStrategy(Calendar calendar) {
		for (DurationStrategy durationStrategy : trafficLight.getDurationStrategies()) {
			if (durationStrategy.getDayOfWeek() == calendar.get(Calendar.DAY_OF_WEEK)) {

				Calendar c = getCalendar(new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
						calendar.get(Calendar.SECOND)));

				if (c.after(getCalendar(durationStrategy.getStart())) && c.before(getCalendar(durationStrategy.getEnd())))
					return durationStrategy;
			}
		}
		return null;
	}

	private void stop() {
		if (scheduledThreadPool != null)
			scheduledThreadPool.shutdown();
		schedule(getStrategy(new GregorianCalendar()));
	}
}
