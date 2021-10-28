package com.epam.jwd.web.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Bike implements Entity {

    private final Long id;
    private final String model;
    private final BigDecimal price;
    private final User owner;

    public Bike(Long id, String model, BigDecimal price, User owner) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.owner = owner;
    }

    public Bike(Long id, String model, BigDecimal price) {
        this(id, model, price, null);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }

    public Bike withOwner(User owner) {
        return new Bike(id, model, price, owner);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(id, bike.id)
                && Objects.equals(model, bike.model)
                && Objects.equals(price, bike.price)
                && Objects.equals(owner, bike.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, price, owner);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", owner=" + owner +
                '}';
    }
}
