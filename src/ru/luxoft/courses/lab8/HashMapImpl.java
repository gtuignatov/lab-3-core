package ru.luxoft.courses.lab8;

public class HashMapImpl {

    private float loadFactor = 0.75f;
    private int capacity = 100;
    private int size = 0;
    private Entry table[] = new Entry[capacity];

    private int hashing(int hashCode) {
        int location = hashCode % capacity;
        System.out.println("Location:" + location);
        return location;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public boolean containsKey(Object key) throws NullPointerException {
        if (key == null && table[0].getKey() == null) {
            return true;
        }
        int location = hashing(key.hashCode());
        Entry e = null;
        try {
            e = table[location];
        }
        catch (NullPointerException ex) {
        }
        if (e!= null && e.getKey() == key) {
            return true;
        }
        return false;
    }

    public boolean containsValue(Object value) throws NullPointerException {
        for (int i=0; i<table.length; i++ ) {
            if (table[i] != null && table[i].getVal() == value) {
                return true;
            }
        }
        return false;
    }

    public Object get(Object key) throws NullPointerException {
        Object ret = null;
        if (key == null) {
            Entry e = null;
            try {
                e = table[0];
            }
            catch (NullPointerException ex) {
            }
            if (e != null) {
                return e.getVal();
            }
            else {
                int location = hashing(key.hashCode());
                Entry eLocation = null;
                try {
                    eLocation = table[location];
                }
                catch (NullPointerException ex) {
                }
                if (eLocation != null && eLocation.getKey() == key) {
                    return eLocation.getVal();
                }
            }
        }
        return ret;
    }

    public Object put(Object key, Object val) throws NullPointerException {
        Object ret = null;
        if (key == null) {
            ret = putForNullKey(val);
            return ret;
        }
        else {
            int location = hashing(key.hashCode());
            if (location >= capacity) {
                System.out.println("Rehashing required!");
                return null;
            }
            Entry e = null;
            try {
                e = table[location];
            }
            catch (NullPointerException ex) {
            }
            if (e!=null && e.getKey() == key) {
                ret = e.getVal();
            }
            else {
                Entry eNew = new Entry();
                eNew.setKey(key);
                eNew.setVal(val);
                table[location] = eNew;
                size++;
            }
        }
        return ret;
    }

    private Object putForNullKey(Object val) throws NullPointerException {
        Entry e = null;
        try {
            e = table[0];
        }
        catch (NullPointerException ex) {
        }
        Object ret = null;
        if (e!=null && e.getKey() == null) {
            ret = e.getVal();
            e.setVal(val);
            return ret;
        }
        else {
            Entry eNew = new Entry();
            eNew.setKey(null);
            eNew.setVal(val);
            table[0] = eNew;
            size++;
        }
        return ret;
    }

    public Object remove(Object key) {
        int location = hashing(key.hashCode());
        Object ret = null;
        if (table[location].getKey() == key) {
            for (int i=location; i<table.length; i++) {
                table[i] = table[i+1];
            }
        }
        return ret;
    }




}
