package ru.luxoft.courses.lab10;

public class Main {

    public static void main(String[] args) {
        System.out.println("Generic List Agregator");
        Integer[] arr = {9, 15, 24};
        GenericListAgregator genericListAgregator = new GenericListAgregator(arr);
        System.out.println("MaxValue: " + genericListAgregator.getMaxValue());
        System.out.println("MinValue: " + genericListAgregator.getMinValue());
        System.out.println("AvgValue: " + genericListAgregator.getAvgValue());
    }
}
