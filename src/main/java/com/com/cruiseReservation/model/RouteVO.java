package com.cruiseReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class RouteVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int routeId;

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @Column(name = "route_depiction", nullable = false)
    private String routeDepiction;

    @Column(name = "route_days", nullable = false)
    private int routeDays;

    // Constructors
    public RouteVO() {}

    public RouteVO(String routeName, String routeDepiction, int routeDays) {
        this.routeName = routeName;
        this.routeDepiction = routeDepiction;
        this.routeDays = routeDays;
    }

    public RouteVO(int routeId, String routeName, String routeDepiction, int routeDays) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.routeDepiction = routeDepiction;
        this.routeDays = routeDays;
    }

    // Getters and Setters
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteDepiction() {
        return routeDepiction;
    }

    public void setRouteDepiction(String routeDepiction) {
        this.routeDepiction = routeDepiction;
    }

    public int getRouteDays() {
        return routeDays;
    }

    public void setRouteDays(int routeDays) {
        this.routeDays = routeDays;
    }

    @Override
    public String toString() {
        return "RouteVO [routeId=" + routeId + ", routeName=" + routeName + ", routeDepiction=" + routeDepiction
                + ", routeDays=" + routeDays + "]";
    }
}
