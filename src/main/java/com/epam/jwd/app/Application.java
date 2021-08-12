package com.epam.jwd.app;

import com.epam.jwd.model.AnimalContext;
import com.epam.jwd.model.Dog;
import com.epam.jwd.model.Mammal;
import com.epam.jwd.model.MammalContext;
import com.epam.jwd.model.MammalFactory;
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

    private static final MammalFactory MAMMAL_FACTORY = MammalFactory.newInstance();

    public static void main(String[] args) {
//        try (ObjectOutputStream out
//                     = new ObjectOutputStream(new FileOutputStream("src/main/resources/test.ob"))) {
//            final Employee tom = new Employee(2, "Bob", 22, new Tail(12));
//            LOG.info("serialization start:");
//            out.writeObject(tom);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        try (ObjectInputStream out
//                     = new ObjectInputStream(new FileInputStream("src/main/resources/test.ob"))) {
//            LOG.info("deserialization start:");
//            final Employee employee = (Employee) out.readObject();
////            employee.initializeTransientFields(...) // bad because one should always remember to do initialization
//            LOG.info("deserialization result: {}", employee);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        final MammalContext mammalContext = MammalContext.of("DOG", "Bobik",
                4, 3, new Tail(2));
        final Mammal mammal = MAMMAL_FACTORY.createMammal(mammalContext);

        Dog.staticField = 100;
        try (ObjectOutputStream out
                     = new ObjectOutputStream(new FileOutputStream("src/main/resources/dog.ob"))) {
            LOG.info("serialization start:");
            out.writeObject(mammal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dog.staticField = 200;


        try (ObjectInputStream out
                     = new ObjectInputStream(new FileInputStream("src/main/resources/dog.ob"))) {
            LOG.info("deserialization start:");
            final Dog dog = (Dog) out.readObject();
//            employee.initializeTransientFields(...) // bad because one should always remember to do initialization
            LOG.info("deserialization result: {}", dog);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
