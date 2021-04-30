package ru.luxoft.courses.lab10;

public class Main {

    public static void main(String[] args) {
        System.out.println("Generic List Agregator");
        Integer[] arr = {9, 15, 24};
        GenericListAggregator<Integer> listAggregator = new GenericListAggregator<>(arr);
        System.out.println("MaxValue: " + listAggregator.getMaxValue());
        System.out.println("MinValue: " + listAggregator.getMinValue());
        System.out.println("AvgValue: " + listAggregator.getAvgValue());
    }
}
