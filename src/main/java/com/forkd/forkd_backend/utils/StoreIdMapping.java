package com.forkd.forkd_backend.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StoreIdMapping {

	private static final Map<Integer, String> ID_MAP;

    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        // Add more mappings here
        ID_MAP = Collections.unmodifiableMap(map);
    }

    public String getTargetId(Integer sourceId) {
        return ID_MAP.get(sourceId);
    }

    public Map<Integer, String> getAllMappings() {
        return ID_MAP;
    }
}
