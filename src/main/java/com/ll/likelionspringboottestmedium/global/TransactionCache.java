package com.ll.likelionspringboottestmedium.global;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.WeakHashMap;

@Service
@RequiredArgsConstructor
public class TransactionCache {
    @PersistenceContext
    private EntityManager entityManager;

    private final WeakHashMap<Object, Object> cache = new WeakHashMap<>();

    public <T> T get(String key) {
        HashMap<String, Object> map = (HashMap<String, Object>) cache.getOrDefault(entityManager.getDelegate(), new HashMap<>());

        return (T) map.get(key);
    }

    public <T> void put(String name, T value) {
        HashMap<String, Object> map = (HashMap<String, Object>) cache.getOrDefault(entityManager.getDelegate(), new HashMap<>());

        if (map.isEmpty()) cache.put(entityManager.getDelegate(), map);

        map.put(name, value);
    }
}
