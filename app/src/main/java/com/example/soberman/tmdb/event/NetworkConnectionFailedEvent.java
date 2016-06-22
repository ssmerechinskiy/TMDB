package com.example.soberman.tmdb.event;

/**
 * Created by soberman on 21.06.2016.
 */
public class NetworkConnectionFailedEvent {
    public String message;
    public NetworkConnectionFailedEvent(String message) {
        this.message = message;
    }
}
