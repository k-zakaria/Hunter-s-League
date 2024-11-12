package com.example.hunters_league.web.errors.hunt;

public class HuntNotFoundException extends RuntimeException {
    public HuntNotFoundException(String huntNotFound) {
        super(huntNotFound);
    }
}
