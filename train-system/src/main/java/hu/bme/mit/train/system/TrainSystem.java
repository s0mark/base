package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

import java.util.Timer;
import java.util.TimerTask;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);

	private int CONTROLLER_UPDATE_PERIOD = 1000;

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

	public TrainSystem() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() { // I do not agree with him on this point
			@Override
			public void run() {
				controller.followSpeed();
			}
		}, 0 /* initial delay */, CONTROLLER_UPDATE_PERIOD);
	}

}
