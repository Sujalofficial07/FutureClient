package com.futureclient.event.events;

import com.futureclient.event.Event;

public class TickEvent extends Event {
    public static class Client extends TickEvent {}
    public static class Player extends TickEvent {}
}
