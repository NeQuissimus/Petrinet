package com.nequissimus.university.k1584.data;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link java.util.Map} implementation with two keys (technically a
 * {@link java.util.HashMap}&lt;K1, {@link java.util.Map}&lt;K2, V&gt;&gt;) that
 * provides some methods for working more comfortably.
 * @author Tim Steinbach
 * @param <K1> Key 1 type
 * @param <K2> Key 2 type
 * @param <V> Value type
 */
public class TwoKeyMap<K1, K2, V> extends HashMap<K1, Map<K2, V>> {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -5366474650582262113L;

    /**
     * Adds a value for the keys key1 and key2 to the map.<br />
     * <br />
     * Algorithm:<br />
     * Find the value map for key1. <br />
     * If no value map exists, create one.<br />
     * Add the pair (key2, value) to that value map.<br />
     * Add the value map to the original map.<br />
     * @param key1 First key
     * @param key2 Second key
     * @param value Value
     * @return Value that was stored previously, NULL if none was stored.
     */
    public final V put(final K1 key1, final K2 key2, final V value) {

        Map<K2, V> tmp = this.get(key1);

        if (null == tmp) {
            tmp = new HashMap<K2, V>();
        }

        final V old = tmp.put(key2, value);
        this.put(key1, tmp);

        return old;

    }

    /**
     * Get a value for the keys key1 and key2.
     * @param key1 First key
     * @param key2 Second key
     * @return Returns the value for the keys, NULL if there is no entry.
     */
    public final V get(final K1 key1, final K2 key2) {

        V result = null;

        final Map<K2, V> tmp = this.get(key1);

        if (null != tmp) {

            result = tmp.get(key2);

        }

        return result;

    }

    /**
     * Remove a value from the map for the given keys.<br />
     * Also, remove the secondary internal map if empty. Otherwise, the isEmpty
     * method would no longer work correctly.
     * @param key1 First key
     * @param key2 Second key
     * @return The value if there was one, NULL otherwise.
     */
    public final V remove(final K1 key1, final K2 key2) {

        V result = null;

        final Map<K2, V> tmp = this.get(key1);

        if (null != tmp) {

            result = tmp.remove(key2);

            if (tmp.isEmpty()) {

                this.remove(key1);

            }

        }

        return result;

    }

}
