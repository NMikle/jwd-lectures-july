package com.epam.jwd.model;

public class AnimalFactory implements MammalFactory {

    AnimalFactory() {
    }

    @Override
    public Mammal createMammal(MammalContext context) {
        Mammal mammal;
        switch (context.getType()) {
            case "DOG":
                mammal = new Dog(context.getName(), context.getAge(), context.getTailLength(), context.getTail());
                break;
            case "CAT":
                mammal = new Cat(context.getName(), context.getAge());
                break;
            default:
                throw new RuntimeException("...");
        }
        return mammal;
    }

}
