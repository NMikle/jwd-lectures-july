package com.epam.jwd.app;

import com.epam.jwd.math.CustomMath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

	private static final Logger LOG = LogManager.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.trace("Program start");
		if (args.length < 2) {
			LOG.error("Not enough arguments");
			System.exit(-1);
		}
		int a = Integer.valueOf(args[0]);
		int b = Integer.valueOf(args[1]);
		int result = CustomMath.crazySum(a, b);
		LOG.info("Result is {}", result);
		LOG.trace("Program end");
	}
}