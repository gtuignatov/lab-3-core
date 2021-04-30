package ru.luxoft.courses.lab10;

public class GenericListAggregator<T extends Number> {
    private final T[] arr;

    public GenericListAggregator(T[] arr) {
        if (arr == null) {
            throw new IllegalStateException("Array must not be a null");
        }
        this.arr = arr;
    }

    public double getAvgValue() {
        double sum = 0.0;
        for (T t : arr) {
            sum += t.doubleValue();
        }
        return sum / arr.length;
    }

    public double getMaxValue() {
        double maxValue = arr[0].doubleValue();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].doubleValue() > maxValue) {
                maxValue = arr[i].doubleValue();
            }
        }
        return maxValue;
    }

    public double getMinValue() {
        double minValue = arr[0].doubleValue();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].doubleValue() < minValue) {
                minValue = arr[i].doubleValue();
            }
        }
        return minValue;
    }

}
