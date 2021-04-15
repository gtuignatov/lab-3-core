package ru.luxoft.courses.lab8;

public class HashMapImpl<K, V> {

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

    public boolean containsKey(K key) throws NullPointerException {
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

    public boolean containsValue(V value) throws NullPointerException {
        for (int i = 0; i < table.length; i++ ) {
            if (table[i] != null && table[i].getVal() == value) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) throws NullPointerException {
        V ret = null;
        if (key == null) {
            Entry e = null;
            try {
                e = table[0];
            }
            catch (NullPointerException ex) {
            }
            if (e != null) {
                return (V) e.getVal();
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
                    return (V) eLocation.getVal();
                }
            }
        }
        return ret;
    }

    public V put(K key, V val) throws NullPointerException {
        V ret = null;
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
                ret = (V) e.getVal();
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

    private V putForNullKey(V value) throws NullPointerException {
        Entry e = null;
        try {
            e = table[0];
        }
        catch (NullPointerException ex) {
        }
        V ret = null;
        if (e!=null && e.getKey() == null) {
            ret = (V) e.getVal();
            e.setVal(value);
            return ret;
        }
        else {
            Entry eNew = new Entry();
            eNew.setKey(null);
            eNew.setVal(value);
            table[0] = eNew;
            size++;
        }
        return ret;
    }

    public V remove(K key) {
        int location = hashing(key.hashCode());
        V ret = null;
        if (table[location].getKey() == key) {
            for (int i = location; i < table.length - 1; i++) {
                table[i] = table[i + 1];
            }
        size--;
        }
        return ret;
    }




}
