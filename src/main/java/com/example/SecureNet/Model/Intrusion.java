//Intrusion
package com.example.SecureNet.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Intrusion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String sourceIp;
    private String targetUrl;
    private String timestamp;
    // No-argument constructor
    public Intrusion() {}
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    // Override toString method
    @Override
    public String toString() {
        return "In2ws trusion{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
