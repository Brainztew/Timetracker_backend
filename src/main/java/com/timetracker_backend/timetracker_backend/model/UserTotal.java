package com.timetracker_backend.timetracker_backend.model;

public class UserTotal {
    private User user;
    private long totalDuration;

    public UserTotal(User user, long totalDuration) {
        this.user = user;
        this.totalDuration = totalDuration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    
    
}
