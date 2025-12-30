package com.futureclient.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<? extends Event>, List<Consumer<? extends Event>>> listeners = new HashMap<>();
    
    public <T extends Event> void subscribe(Class<T> eventClass, Consumer<T> listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Event> void post(T event) {
        List<Consumer<? extends Event>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (Consumer<? extends Event> listener : eventListeners) {
                ((Consumer<T>) listener).accept(event);
            }
        }
    }
}
