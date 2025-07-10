package com.acme.jobconnect.platform.users.domain.model.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Availability {
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public Availability() {
    }

    public Availability(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }
}