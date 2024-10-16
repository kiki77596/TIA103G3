package com;

import javax.persistence.*;

import com.cruiseReservation.model.RouteVO;

import java.time.LocalDateTime;

@Entity
@Table(name = "ships_schedule")
public class ShipsScheduleVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_id")
    private int shipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private RouteVO route;

    @Column(name = "ship_status", nullable = false)
    private String shipStatus;

    @Column(name = "ship_shipping_time", nullable = false)
    private LocalDateTime shipShippingTime;

    @Column(name = "ship_shipping_dock", nullable = false)
    private String shipShippingDock;

    @Column(name = "ship_rooms_booked", nullable = false)
    private int shipRoomsBooked;

    // Constructors

    public ShipsScheduleVO() {
    }

    public ShipsScheduleVO(RouteVO route, String shipStatus, LocalDateTime shipShippingTime, String shipShippingDock, int shipRoomsBooked) {
        this.route = route;
        this.shipStatus = shipStatus;
        this.shipShippingTime = shipShippingTime;
        this.shipShippingDock = shipShippingDock;
        this.shipRoomsBooked = shipRoomsBooked;
    }

    // Getters and Setters

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public RouteVO getRoute() {
        return route;
    }

    public void setRoute(RouteVO route) {
        this.route = route;
    }

    public String getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(String shipStatus) {
        this.shipStatus = shipStatus;
    }

    public LocalDateTime getShipShippingTime() {
        return shipShippingTime;
    }

    public void setShipShippingTime(LocalDateTime shipShippingTime) {
        this.shipShippingTime = shipShippingTime;
    }

    public String getShipShippingDock() {
        return shipShippingDock;
    }

    public void setShipShippingDock(String shipShippingDock) {
        this.shipShippingDock = shipShippingDock;
    }

    public int getShipRoomsBooked() {
        return shipRoomsBooked;
    }

    public void setShipRoomsBooked(int shipRoomsBooked) {
        this.shipRoomsBooked = shipRoomsBooked;
    }

    @Override
    public String toString() {
        return "ShipsScheduleVO{" +
                "shipId=" + shipId +
                ", route=" + (route != null ? route.getRouteId() : "null") +
                ", shipStatus='" + shipStatus + '\'' +
                ", shipShippingTime=" + shipShippingTime +
                ", shipShippingDock='" + shipShippingDock + '\'' +
                ", shipRoomsBooked=" + shipRoomsBooked +
                '}';
    }
}
