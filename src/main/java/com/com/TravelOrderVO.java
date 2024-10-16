package com;

import com.cruiseReservation.model.CouponTypeVO;
import com.cruiseReservation.model.MemberDataVO;

public class TravelOrderVO {
    
    private Integer travOrdeId; // travel_order_id
    private Integer membId;     // member ID (foreign key)
    private Integer shipId;     // ship ID (foreign key)
    private Integer coupId;     // coupon ID (foreign key)
    private String travOrdeStatus;  // travel order status
    private Integer roomAmount;     // number of rooms booked
    private String travOrdeAmount;  // total travel order amount

    // Constructor
    public TravelOrderVO() {}

    // Getters and Setters
    public Integer getTravOrdeId() {
        return travOrdeId;
    }

    public void setTravOrdeId(Integer travOrdeId) {
        this.travOrdeId = travOrdeId;
    }

    public Integer getMembId() {
        return membId;
    }

    public void setMembId(Integer membId) {
        this.membId = membId;
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getCoupId() {
        return coupId;
    }

    public void setCoupId(Integer coupId) {
        this.coupId = coupId;
    }

    public String getTravOrdeStatus() {
        return travOrdeStatus;
    }

    public void setTravOrdeStatus(String travOrdeStatus) {
        this.travOrdeStatus = travOrdeStatus;
    }

    public Integer getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(Integer roomAmount) {
        this.roomAmount = roomAmount;
    }

    public String getTravOrdeAmount() {
        return travOrdeAmount;
    }

    public void setTravOrdeAmount(String travOrdeAmount) {
        this.travOrdeAmount = travOrdeAmount;
    }

    // ToString method (for debugging)
    @Override
    public String toString() {
        return "TravelOrderVO [travOrdeId=" + travOrdeId + ", membId=" + membId + ", shipId=" + shipId + 
               ", coupId=" + coupId + ", travOrdeStatus=" + travOrdeStatus + 
               ", roomAmount=" + roomAmount + ", travOrdeAmount=" + travOrdeAmount + "]";
    }

	public void setMemberData(MemberDataVO member) {
		// TODO Auto-generated method stub
		
	}

	public void setShipsSchedule(ShipsScheduleVO ship) {
		// TODO Auto-generated method stub
		
	}

	public void setCouponType(CouponTypeVO coupon) {
		// TODO Auto-generated method stub
		
	}

	public void setTravOrdeAmount(double totalAmount) {
		// TODO Auto-generated method stub
		
	}
}
