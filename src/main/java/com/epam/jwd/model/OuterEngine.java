package com.epam.jwd.model;

public class OuterEngine extends Car.StaticEngine {
    @Override
    public void launch() {
//        System.out.println(Car.SOME_CONST);//impossible because SOME_CONST has private access modifier
    }
}
