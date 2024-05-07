package com.timetracker_backend.timetracker_backend.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private List<Interval> intervals = new ArrayList<>();
    private boolean timerRunning = false;
    @DBRef
    private User user;
    private String userId;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public User getUser() {
         return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isTimerRunning() {
        return timerRunning;
    }
    public void setTimerRunning(boolean timerRunning) {
        this.timerRunning = timerRunning;
    }
    public Task(String id, String name, List<Interval> intervals) {
        this.id = id;
        this.name = name;
        this.intervals = intervals;
    }
    public Task() {
    }
    public List<Interval> getIntervals() {
        return intervals;
    }
    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getTotalDuration() {
        return intervals.stream()
            .mapToLong(Interval::getDuration)
            .sum();
    }

    public void start() {
        intervals.add(new Interval());
    }

    public void end() {
        Interval lastInterval = intervals.get(intervals.size() - 1);
        lastInterval.setEndTime(LocalDateTime.now());
    }

  
    @Document
    public static class Interval {

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startTime = LocalDateTime.now();

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endTime;
        
        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
        
        public LocalDateTime getStartTime() {
            return startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public long getDuration() {
            if (endTime != null) {
                Duration duration = Duration.between(startTime, endTime);
                return duration.toMinutes();
            } else {
                return 0;
            }
        }

        
    }

}
