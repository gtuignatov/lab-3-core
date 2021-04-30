package ru.luxoft.courses.lab11.model.score;

import ru.luxoft.courses.lab11.Loggable;
import ru.luxoft.courses.lab11.MethodLimit;
import ru.luxoft.courses.lab11.model.accounts.Account;
import ru.luxoft.courses.lab11.model.money.Money;
import ru.luxoft.courses.lab11.model.money.MoneyInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {
    private static final String LIMIT_IS_OVER_MSG = "Method Limit Is Over";
    private Money balance;
    private Account owner;
    private Integer number;
    private final Map<String, Integer> methodConstraintMap = new HashMap<>();
    private final Map<String, Integer> methodCallMap = new HashMap<>();
    private boolean methodConstraintToggle;

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
        Class<? extends Score> thisClass = this.getClass();
        for (Method method : thisClass.getDeclaredMethods()) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation instanceof MethodLimit) {
                    int limit = ((MethodLimit) annotation).limit();
                    methodConstraintMap.put(method.getName(), limit);
                    methodCallMap.put(method.getName(), 0);
                    methodConstraintToggle = true;
                }
            }
        }
    }

    public Money getBalance() {
        logIfNeeded("getBalance");
        if (!isMethodAvailableByOperLimit("getBalance")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return balance;
    }

    public void setBalance(Money balance) {
        logIfNeeded("setBalance");
        if (!isMethodAvailableByOperLimit("setBalance")) {
            System.out.println(LIMIT_IS_OVER_MSG);
        } else {
            this.balance = balance;
        }
    }

    public Account getOwner() {
        logIfNeeded("getOwner");
        if (!isMethodAvailableByOperLimit("getOwner")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return owner;
    }

    public void setOwner(Account owner) {
        logIfNeeded("setOwner");
        if (!isMethodAvailableByOperLimit("setOwner")) {
            System.out.println(LIMIT_IS_OVER_MSG);
        } else {
            this.owner = owner;
        }
    }

    public Integer getNumber() {
        logIfNeeded("getNumber");
        if (!isMethodAvailableByOperLimit("getNumber")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return number;
    }

    public void setNumber(Integer number) {
        logIfNeeded("setNumber");
        if (!isMethodAvailableByOperLimit("setNumber")) {
            System.out.println(LIMIT_IS_OVER_MSG);
        } else {
            this.number = number;
        }
    }

    @Override
    public void addMoney(Money money) {
        logIfNeeded("addMoney");
        if (!isMethodAvailableByOperLimit("addMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
        } else {
            double usdValueIn = money.getValue() * money.getCurrency().getUsdCourse();
            double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCourse();

            if (usdValueThis < usdValueIn) {
                System.out.println("You have no so much!");
                return;
            }

            if (checkBefore()) {
                this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCourse());
            } else {
                System.out.println("No Check!");
            }
        }
    }

    // Do not understand What For This Method?
    protected abstract boolean checkBefore();

    @Override
    public Money getMoney(double balanceLess) {
        logIfNeeded("getMoney");
        if (!isMethodAvailableByOperLimit("getMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Wrong Balance Less");
        }
        this.balance.setValue(this.balance.getValue() - balanceLess);
        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess() {
        logIfNeeded("getMoneyWithoutLess");
        if (!isMethodAvailableByOperLimit("getMoneyWithoutLess")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return this.balance;
    }

    protected void logIfNeeded(String methodName) {
        Class<? extends Score> thisClass = this.getClass();
        for (Annotation annotation : thisClass.getAnnotations()) {
            if (annotation instanceof Loggable) {
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName) {
        if (methodConstraintMap.containsKey(methodName)) {
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);
            if (currentCalls >= limitCalls) {
                return false;
            }
            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return true;
    }

}
