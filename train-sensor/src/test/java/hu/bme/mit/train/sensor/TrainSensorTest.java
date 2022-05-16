package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController controller;

    TrainUser user;

    private TrainSensor sensor;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);

        when(controller.getReferenceSpeed()).thenReturn(0);
    }

    @Test
    public void testAlarmNegative() {
        sensor.overrideSpeedLimit(-1);
        verify(user).setAlarmState(true);
    }

    @Test
    public void testAlarmSmallerThan50Percent() {
        sensor.overrideSpeedLimit(150);
        verify(user).setAlarmState(false);
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(user).setAlarmState(true);
    }

    @Test
    public void testAlarmNoAlarm() {
        sensor.overrideSpeedLimit(50);
        verify(user).setAlarmState(false);
    }

    @Test
    public void testAlarmTooBig() {
        sensor.overrideSpeedLimit(501);
        verify(user).setAlarmState(true);
    }
}
