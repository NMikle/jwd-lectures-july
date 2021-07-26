package com.epam.jwd.app;

import com.epam.jwd.model.Animal;
import com.epam.jwd.model.Cat;
import com.epam.jwd.model.Dog;
import com.epam.jwd.model.Mammal;
import com.epam.jwd.model.Tail;
import com.epam.jwd.service.AnimalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);
    private static final int DEFAULT_WOOL_LENGTH = 1;
    private static final String TEST_DOG_NAME = "Bobik";
    private static final int TEST_DOG_AGE = 2;
    private static final int TEST_DOG_TAIL_LENGTH = 3;
    private static final int CUSTOM_TAIL_WOOL_LENGTH_FOR_TEST = 2;

    public static void main(String[] args) {

        Animal bobik = new Dog(TEST_DOG_NAME, TEST_DOG_AGE,
                TEST_DOG_TAIL_LENGTH, new Tail(CUSTOM_TAIL_WOOL_LENGTH_FOR_TEST));
        final Cat barsik = new Cat("Barsik", 8);
//        Animal cat = new Animal("Barsik", 4); // Does not work if Animal is abstract!

        final AnimalService animalService = new AnimalService();

        animalService.makeAnimalDoNoises(bobik);
        animalService.makeAnimalDoNoises(barsik);

        animalService.makeMammalMove(bobik);
        animalService.makeMammalMove(barsik);

    }

}