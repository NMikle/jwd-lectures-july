package com.epam.jwd.web.model;

import java.util.Objects;

public class Bike {

    private final Integer id;
    private final String model;

    public Bike(Integer id, String model) {
        this.id = id;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(id, bike.id) && Objects.equals(model, bike.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
