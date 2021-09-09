package com.epam.jwd.model;


public class Car {

    private static final String SOME_CONST = "const value";

    private String model;
    private AwesomeEngine engine;

    public Car(String model) {
        this.model = model;
        this.engine = new AwesomeEngine("from constructor");
    }

    public void drive() {
        if (engine == null) {
            throw new IllegalStateException("engine null");
        }
        engine.launch();
        System.out.println("drive started");
    }

    public abstract class CarEngine {
        private String name;

        public abstract void launch();

        class Inner {
            public void tmp() {
                Inner i = new Inner(); // inner not abstract
            }
        }

//        static class Nested { //cannot define nested class inside inner class
//        }
    }

    public class AwesomeEngine extends CarEngine {
        //        private static String tmp = ""; // static keyword is impossible to use inside inner class
        private String model;

        public AwesomeEngine(String model) {
            this.model = model;
        }

        @Override
        public void launch() {
            System.out.println("awesome engine launched on car " + Car.this.model);
            System.out.println("engine model: " + this.model); // same as model
            System.out.println(Car.this.engine.model);
        }

//        public static int hey() {// static keyword is impossible to use inside inner class
//            return 3;
//        }
    }

    public static abstract class StaticEngine {
        public abstract void launch();

        public void instanceMethod() {
            System.out.println(SOME_CONST);
        }

        static class Nested {
            static class NestedNested {

            }
        }

        class Inner {
        }
    }

    public static class StaticEngineChild extends StaticEngine {

        @Override
        public void launch() {
            final Car car = new Car("");
            System.out.println(car.engine);
//            System.out.println(engine); //impossible
        }
    }

    public void testLocalClass() {
        class Hey extends CarEngine { // inner class or local class
            private final static String a = "";
            private String someField;

            @Override
            public void launch() {
                System.out.println(model);
            }

            class Another {
                class InnerInnerInner {
                }
            }
        }

        Hey h = new Hey();
        h.someField = "someValue";
    }

    public enum NestedEnum {
        ONE, TWO, THREE;
    }

}
