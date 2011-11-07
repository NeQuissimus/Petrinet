package com.nequissimus.university.k1584.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bidirectional map that allows retrieval in both directions. Since this map is
 * implemented by using two HashMaps internally, the user needs to ensure the
 * uniqueness of all keys and values. Otherwise there may be discrepancies when
 * retrieving via values.
 * @author Tim Steinbach
 * @param <K> Key type
 * @param <V> Value type
 */
public class BiMap<K, V> implements Map<K, V> {

    /**
     * Map keys to values.
     */
    private final Map<K, V> keyToValue = new HashMap<K, V>();

    /**
     * Map values to keys.
     */
    private final Map<V, K> valueToKey = new HashMap<V, K>();

    @Override
    public final void clear() {
        this.keyToValue.clear();
        this.valueToKey.clear();
    }

    @Override
    public final boolean containsKey(final Object key) {
        return this.keyToValue.containsKey(key);
    }

    @Override
    public final boolean containsValue(final Object value) {
        return this.keyToValue.containsValue(value);
    }

    @Override
    public final Set<java.util.Map.Entry<K, V>> entrySet() {
        return this.keyToValue.entrySet();
    }

    @Override
    public final V get(final Object key) {
        return this.keyToValue.get(key);
    }

    /**
     * Get the key for a certain value.
     * @param value Value
     * @return Key
     */
    public final K getKey(final V value) {

        return this.valueToKey.get(value);

    }

    @Override
    public final boolean isEmpty() {
        return this.keyToValue.isEmpty();
    }

    @Override
    public final Set<K> keySet() {
        return this.keyToValue.keySet();
    }

    /**
     * Get the set of values for this map.
     * @return Set of values
     */
    public final Set<V> valueSet() {

        return this.valueToKey.keySet();

    }

    @Override
    public final V put(final K key, final V value) {

        this.valueToKey.put(value, key);
        return this.keyToValue.put(key, value);

    }

    @Override
    public final void putAll(final Map<? extends K, ? extends V> map) {

        final Set<? extends K> keys = map.keySet();
        for (final K k : keys) {

            this.valueToKey.put(map.get(k), k);

        }

        this.keyToValue.putAll(map);

    }

    @Override
    public final V remove(final Object key) {

        final V value = this.keyToValue.remove(key);
        this.valueToKey.remove(value);

        return value;

    }

    /**
     * Remove a value and its corresponding key.
     * @param value Value
     * @return Removed key
     */
    public final K removeValue(final V value) {

        final K key = this.valueToKey.remove(value);
        this.keyToValue.remove(key);

        return key;

    }

    @Override
    public final int size() {
        return this.keyToValue.size();
    }

    @Override
    public final Collection<V> values() {
        return this.keyToValue.values();
    }

    /**
     * Return the collection of keys.
     * @return Keys
     */
    public final Collection<K> keys() {

        return this.valueToKey.values();

    }

}
