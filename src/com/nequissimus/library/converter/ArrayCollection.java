package com.nequissimus.library.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Turn an array into a corresponding collection.
 * @author: Tim Steinbach
 * @param <T> Element type
 */
public final class ArrayCollection<T> {

    /**
     * Turn the array into a list.
     * @param array Array to be turned into a list
     * @return List List with array elements
     */
    public List<T> toList(final T[] array) {

        final List<T> tmp = new ArrayList<T>();

        Collections.addAll(tmp, array);

        return tmp;

    }

}
