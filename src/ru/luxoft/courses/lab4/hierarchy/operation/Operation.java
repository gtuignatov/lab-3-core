package ru.luxoft.courses.lab4.hierarchy.operation;

import ru.luxoft.courses.lab4.hierarchy.Emmitent;

public class Operation {
    private OperType operType;
    private String name;
    private Emmitent emmitent;

    public Operation(OperType operType, String name, Emmitent emmitent) {
        this.operType = operType;
        this.name = name;
        this.emmitent = emmitent;
    }

    public OperType getOperType() {
        return operType;
    }

    public void setOperType(OperType operType) {
        this.operType = operType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emmitent getEmmitent() {
        return emmitent;
    }

    public void setEmmitent(Emmitent emmitent) {
        this.emmitent = emmitent;
    }

}
