package ru.luxoft.courses.lab11;

import java.lang.annotation.Annotation;

public class ATM {

    private int operLimit;
    private int currenOpers;
    private boolean operLimitToggle;

    public ATM() {
        Class thisClass = this.getClass();
        for (Annotation annotation : thisClass.getAnnotations()) {
            if (annotation instanceof OperationLimitATM) {
                this.operLimit = ((OperationLimitATM) annotation).limit();
                this.operLimitToggle = true;
            }
        }
    }
}
