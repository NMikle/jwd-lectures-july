package com.epam.jwd.model;

public interface MammalFactory {

    Mammal createMammal(MammalContext context);

    static MammalFactory newInstance() {
        return new AnimalFactory();
    }

}
