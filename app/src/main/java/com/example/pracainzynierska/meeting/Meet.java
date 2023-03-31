package com.example.pracainzynierska.meeting;

import java.util.Calendar;

public class Meet {
    private String meetTitle;
    private String meetDescription;
    private String meetLocation;
    private Calendar cal;

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public String getMeetDescription() {
        return meetDescription;
    }

    public void setMeetDescription(String meetDescription) {
        this.meetDescription = meetDescription;
    }

    public String getMeetLocation() {
        return meetLocation;
    }

    public void setMeetLocation(String meetLocation) {
        this.meetLocation = meetLocation;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }
}
