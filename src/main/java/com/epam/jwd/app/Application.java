package com.epam.jwd.app;

import com.epam.jwd.model.Employee;
import com.epam.jwd.model.Tail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        try (ObjectOutputStream out
                     = new ObjectOutputStream(new FileOutputStream("src/main/resources/test.ob"))) {
            final Employee tom = new Employee(2, "Bob", 22, new Tail(12));
            out.writeObject(tom);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream out
                     = new ObjectInputStream(new FileInputStream("src/main/resources/test.ob"))) {
            final Employee employee = (Employee) out.readObject();
            System.out.println(employee);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
