/*
 * *** Abdul Shad / 272 ***
 *
 * This hashMap object represents an over-simplification of Java's implementation of HashMap within
 * Java's Collection Framework Library. You are to complete the following methods:
 *  - remove(K)
 *  - replace(K,V)
 *  - replace(K,V,V)
 *
 * In addition to the documentation below, you can read the online Java documentation for HashMap for
 * the expected behavior/return values of each method below. This object follows the same behavior
 * as those methods implemented in Java's library.
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Class HashNode
 *
 * Node object representing a <Key, Value> pair stored in the Hash Map.
 * Elements hashed to the same bucket slot will be chained through a singly linked-list.
 */
class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

/**
 * A simple implementation of a HashMap that emulates the Map Interface.
 * Uses separate chaining for collision handling.
 */
class myHashMap<K, V> {

    private static final float DEFAULT_LOAD_FACTOR = 0.7f;
    private static final int INITIAL_NUM_BUCKETS = 10;

    ArrayList<HashNode<K, V>> bucket;
    int numBuckets;
    int size;

    public myHashMap() {
        numBuckets = INITIAL_NUM_BUCKETS;
        size = 0;
        bucket = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7fffffff) % numBuckets;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; // Update existing key
                return;
            }
            head = head.next;
        }

        // Insert new node at the head of the chain
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = bucket.get(index);
        bucket.set(index, newNode);
        size++;

        // Check load factor and rehash if needed
        if ((1.0 * size) / numBuckets > DEFAULT_LOAD_FACTOR) {
            rehash();
        }
    }

    private void rehash() {
        ArrayList<HashNode<K, V>> oldBucket = bucket;
        bucket = new ArrayList<>();
        numBuckets *= 2;
        size = 0;

        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }

        for (HashNode<K, V> headNode : oldBucket) {
            while (headNode != null) {
                put(headNode.key, headNode.value);
                headNode = headNode.next;
            }
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (HashNode<K, V> head : bucket) {
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    bucket.set(index, head.next);
                } else {
                    prev.next = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null; // Key not found
    }

    public V replace(K key, V newValue) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);

        while (head != null) {
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = newValue;
                return oldValue;
            }
            head = head.next;
        }
        return null; // Key not found
    }

    public boolean replace(K key, V oldValue, V newValue) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);

        while (head != null) {
            if (head.key.equals(key) && head.value.equals(oldValue)) {
                head.value = newValue;
                return true;
            }
            head = head.next;
        }
        return false; // Key not found or old value does not match
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (HashNode<K, V> head : bucket) {
            while (head != null) {
                keys.add(head.key);
                head = head.next;
            }
        }
        return keys;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (HashNode<K, V> head : bucket) {
            while (head != null) {
                entries.add(Map.entry(head.key, head.value));
                head = head.next;
            }
        }
        return entries;
    }
}
