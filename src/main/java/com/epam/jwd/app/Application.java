package com.epam.jwd.app;

import com.epam.jwd.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        Car car = new Car("aaa");
//        Car.CarEngine engine = new Car.AwesomeEngine();
        Car.CarEngine engine = car.new AwesomeEngine("from main");
        car.drive();
        engine.launch();

        final Car.StaticEngineChild staticChild = new Car.StaticEngineChild();

        Iterator<Integer> i = new Iterator<Integer>() {
//            static class Nested {} // nested classes could not be present inside anonymous class
            class Inner {}
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Integer next() {
                return null;
            }
        };
    }

}
