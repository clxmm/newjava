package org.clxmm.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020-09-06 16:55
 */
public class LRUMap<K, V> extends LinkedHashMap<K, V> {


    private int maxSize;

    public LRUMap(int maxSize) {
        super(16, 0.75f, true);
        this.maxSize = maxSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.maxSize;
    }
}
