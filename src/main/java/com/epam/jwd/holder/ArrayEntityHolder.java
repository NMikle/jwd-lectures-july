package com.epam.jwd.holder;

import com.epam.jwd.model.Entity;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

public class ArrayEntityHolder<T extends Entity<T>> implements EntityHolder<T> {

    private static final int INITIAL_USER_AMOUNT = 8;
    private static final int GROWTH_FACTOR = 4;

    private final Function<Integer, T[]> arrayCreatorFunction;

    private T[] elements;
    private int size;

    public ArrayEntityHolder(Function<Integer, T[]> arrayCreatorFunction) {
        this.arrayCreatorFunction = arrayCreatorFunction;
        initialize(arrayCreatorFunction);
    }

    @Override
    public int save(T element) {
        growIfLimitReached();
        elements[size++] = element;
        return size;
    }

    @Override
    public T save(T element, int index) {
        if (!isInBorders(index) || size < --index) {
            return null;
        }
        growIfLimitReached();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        return element;
    }

    @Override
    public T retrieve(int index) {
        return isInBorders(index) ? elements[--index] : null;
    }

    @Override
    public int remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            this.remove(++index);
        }
        return index;
    }

    @Override
    public T remove(int index) {
        T removed = null;
        if (isInBorders(index)) {
            removed = elements[--index];
            elements[index] = null;
            System.arraycopy(elements, index + 1, elements, index, size - index);
            size--;
        }
        return removed;
    }

    public int indexOf(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            final T savedUser = elements[i];
            if (savedUser.equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        initialize(this.arrayCreatorFunction);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return size > pointer;
            }

            @Override
            public T next() {
                return elements[pointer++];
            }
        };
    }

    private void initialize(Function<Integer, T[]> arrayCreatorFunction) {
        this.elements = arrayCreatorFunction.apply(INITIAL_USER_AMOUNT);
        this.size = 0;
    }

    private void growIfLimitReached() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, size + GROWTH_FACTOR);
        }
    }

    private boolean isInBorders(int index) {
        return index < elements.length + 1 && index > 0;
    }

}
