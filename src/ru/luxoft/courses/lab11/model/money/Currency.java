package ru.luxoft.courses.lab11.model.money;

public class Currency {
    private String name;
    private float usdCourse;

    public Currency(String name, float usdCourse) {
        this.name = name;
        this.usdCourse = usdCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUsdCourse() {
        return usdCourse;
    }

    public void setUsdCourse(float usdCourse) {
        this.usdCourse = usdCourse;
    }
}
