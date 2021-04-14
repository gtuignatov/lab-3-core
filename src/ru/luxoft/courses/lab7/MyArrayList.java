package ru.luxoft.courses.lab7;

import java.util.Arrays;

public class MyArrayList<T> {

    private static final int INIT_SIZE = 16;
    private static final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public void add(T item) {
        if (pointer == array.length - 1) {
            resize(array.length * 2);
            array[pointer++] = item;
        }
    }

    private void resize(int newLength) {
        array = Arrays.copyOf(array, newLength);
    }

    public T get(int index) {
        @SuppressWarnings("unchecked")
        T t = (T) array[index];
        return t;
    }

    public int size() {
        return pointer;
    }

    public void remove(int index) {
        if (pointer >= index) {
            System.arraycopy(array, index + 1, array, index, pointer - index);
        }
        array[pointer--] = null;

        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            resize(array.length / 2);
        }
    }
}
