package com.epam.jwd.service;

import com.epam.jwd.model.Animal;
import com.epam.jwd.model.Mammal;

public enum AnimalService {
    INSTANCE;

    public void makeAnimalDoNoises(Animal animal) {
        System.out.printf("Animal %s makes noise:\n", animal.getName());
        animal.makeNoise();
    }

    public void makeMammalMove(Mammal mammal) {
        System.out.printf("Animal %s is going to move:\n", mammal.toString());
        mammal.move();
    }

}
