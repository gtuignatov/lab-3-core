package ru.luxoft.courses.lab4.hierarchy.operation;

import java.util.List;

public class Journal {
    private List<Operation> operations;

    public Journal(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

}
