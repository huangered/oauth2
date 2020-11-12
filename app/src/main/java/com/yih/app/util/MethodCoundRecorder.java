package com.yih.app.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public final class MethodCoundRecorder {
    private static Map<String, Map<String, Long>> recorders = new ConcurrentHashMap<>();

    public static void record(String method) {
        log.info("{}", Thread.currentThread().getName());
        String key = Thread.currentThread().getName();
        recorders.putIfAbsent(key, new HashMap<>());
        recorders.get(key).merge(method, 1L, Long::sum);
    }

    public static void print() {
        Map<String, Long> answer = recorders.values()
                .stream()
                .reduce((left, right) -> {
                    Map<String, Long> total = new HashMap<>(left);
                    right.forEach((s, aLong) -> total.merge(s, aLong, Long::sum));

                    return total;
                }).get();

        log.info("{}", answer);
    }
}