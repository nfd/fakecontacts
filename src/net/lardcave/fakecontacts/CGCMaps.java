package net.lardcave.fakecontacts;

import java.util.HashMap;

// wzdd -- clagged from com.google.collect.Maps AOSP
/**
 * Provides static methods for creating mutable {@code Maps} instances easily.
 */
public class CGCMaps {
    /**
     * Creates a {@code HashMap} instance.
     *
     * @return a newly-created, initially-empty {@code HashMap}
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }
}