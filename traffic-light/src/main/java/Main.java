import java.util.List;

import com.globoforce.dao.TrafficLightDao;
import com.globoforce.entity.TrafficLight;
import com.globoforce.entity.TrafficLightScheduler;

public class Main {

	public static void main(String[] args) {

		TrafficLightDao dao = new TrafficLightDao();
		dao.generateData();

		List<TrafficLight> trafficLights = dao.findAll();
		dao.closeEntityManager();

		for (TrafficLight trafficLight : trafficLights) {
			TrafficLightScheduler scheduler = new TrafficLightScheduler(trafficLight);
			scheduler.start();

		}
	}

}
