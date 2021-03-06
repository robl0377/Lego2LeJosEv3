/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.GyroSensor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the gyro sensor.
 * 
 * @author Roland Blochberger
 */
public class GyroSensorTest {

	private static Class<?> clazz = GyroSensorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	// the sensor configuration
	static final Port gyroSensorPort = SensorPort.S2;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the gyro sensor
		GyroSensor gyroSensor = new GyroSensor(gyroSensorPort);
		// reset it
		gyroSensor.reset();

		Display.textGrid("Gyro Sensor", true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// -----------------------------------------------
		// gyro rate display
		float[] value = new float[2];
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get gyro rate value and display it on the LCD
			value[0] = gyroSensor.measureRate();
			Display.textGrid("Rate: " + value[0] + "   ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// gyro angle display
		while (Button.ENTER.isUp()) {
			// get gyro angle value and display it on the LCD
			value[0] = gyroSensor.measureAngle();
			Display.textGrid("Angle: " + value[0] + "   ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// gyro rate and angle display
		while (Button.ENTER.isUp()) {
			// get gyro angle value and display it on the LCD
			value = gyroSensor.measureAngleRate();
			Display.textGrid("Rate : " + value[0] + "   ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Angle: " + value[1] + "   ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		log.fine("The End");
	}

}
