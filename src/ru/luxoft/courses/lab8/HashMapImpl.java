package ru.luxoft.courses.lab8;

public class HashMapImpl<K, V> {

    private static final int CAPACITY = 100;
    @SuppressWarnings("unchecked")
    private final Entry<K, V>[] table = new Entry[CAPACITY];
    private int size = 0;

    private int hashing(int hashCode) {
        int location = hashCode % CAPACITY;
        System.out.println("Location:" + location);
        return location;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsKey(K key) throws NullPointerException {
        if (key == null && table[0].getKey() == null) {
            return true;
        }
        int location = hashing(key.hashCode());
        Entry<K, V> e = table[location];
        return e != null && e.getKey() == key;
    }

    public boolean containsValue(V value) throws NullPointerException {
        for (Entry<K, V> entry : table) {
            if (entry != null && entry.getVal() == value) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) throws NullPointerException {
        if (key == null) {
            Entry<K, V> e = table[0];
            if (e != null) {
                return e.getVal();
            }
        } else {
            int location = hashing(key.hashCode());
            Entry<K, V> eLocation = table[location];
            if (eLocation != null && eLocation.getKey() == key) {
                return eLocation.getVal();
            }
        }
        return null;
    }

    public V put(K key, V val) throws NullPointerException {
        V ret = null;
        if (key == null) {
            ret = putForNullKey(val);
            return ret;
        } else {
            int location = hashing(key.hashCode());
            if (location >= CAPACITY) {
                System.out.println("Rehashing required!");
                return null;
            }
            Entry<K, V> e = table[location];
            if (e != null && e.getKey() == key) {
                ret = e.getVal();
            } else {
                Entry<K, V> eNew = new Entry<>();
                eNew.setKey(key);
                eNew.setVal(val);
                table[location] = eNew;
                size++;
            }
        }
        return ret;
    }

    private V putForNullKey(V value) throws NullPointerException {
        Entry<K, V> e = table[0];
        V ret = null;
        if (e != null && e.getKey() == null) {
            ret = e.getVal();
            e.setVal(value);
            return ret;
        } else {
            Entry<K, V> eNew = new Entry<>();
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
            ret = table[location].getVal();
            if (table.length - 1 - location >= 0) {
                System.arraycopy(table, location + 1, table, location, table.length - 1 - location);
            }
            size--;
        }
        return ret;
    }

}
