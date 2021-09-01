package com.epam.jwd.app;

import com.epam.jwd.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SoftReference<Employee> softlyReachableBob = new SoftReference<>(
                new Employee(1, "Bob", 42, null)
        );
        System.out.println(softlyReachableBob.get());
        final WeakReference<String> weaklyReachableStr = new WeakReference<>("Hello World");
        System.out.println(weaklyReachableStr.get());
    }

}
