package ru.luxoft.courses.lab8;

public class Main {

    public static void main(String[] args) {
        System.out.println("My HashMap With Generic");
        HashMapImpl<Integer, String> myHashMap = new HashMapImpl<>();
        //todo ????????
        System.out.println(myHashMap.containsKey(null));

        myHashMap.put(null, "Zero");
        myHashMap.put(5, "Five");
        myHashMap.put(7, "Seven");
        myHashMap.put(10, "Ten");
        System.out.println("Contains Key 5: " + myHashMap.containsKey(5));
        System.out.println("Size: " + myHashMap.size());
        System.out.println("Remove Entry With Key 7");
        myHashMap.remove(7);
        System.out.println("New Size: " + myHashMap.size());
        System.out.println("Put 100");
        myHashMap.put(100, "One Hundred");
        myHashMap.put(7, "Seven");
        System.out.println("Size: " + myHashMap.size());
        System.out.println("Put 7 again");
        myHashMap.put(7, "Seven");
        System.out.println("Final Size: " + myHashMap.size());
    }
}
