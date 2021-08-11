package com.epam.jwd.app;

import com.epam.jwd.model.Employee;
import com.epam.jwd.model.Tail;
import com.epam.jwd.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
//        final User boris = new User(1, "Boris", 41);
//        final User anotherBoris = boris.clone();
//        System.out.println(anotherBoris);
        final Employee alice = new Employee(1, "Alice", 43, new Tail(3));
        System.out.println("cloning");
        final Employee aliceClone = (Employee) alice.clone();
        aliceClone.getTail().setWoolLength(5);
        System.out.println(alice);
        System.out.println(aliceClone);
    }
}
