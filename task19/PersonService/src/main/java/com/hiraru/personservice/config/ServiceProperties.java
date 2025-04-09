// person-service/src/main/java/com/hiraru/person/config/ServiceProperties.java

package com.hiraru.personservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.services")
public class ServiceProperties {
    private Location location = new Location();

    // Getters and Setters
    public static class Location {
        private String baseUrl;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}