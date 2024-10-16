package com.cruiseReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "coupon_type")
public class CouponTypeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coup_id")
    private int coupId;

    @Column(name = "coupon_code", nullable = false, unique = true)
    private String couponCode;

    // 如果有其他屬性，可以在此添加
    // 例如：
    // @Column(name = "discount_percentage")
    // private double discountPercentage;

    // Constructors
    public CouponTypeVO() {
    }

    public CouponTypeVO(String couponCode) {
        this.couponCode = couponCode;
    }

    // Getters and Setters

    public int getCoupId() {
        return coupId;
    }

    public void setCoupId(int coupId) {
        this.coupId = coupId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    // 如果有其他屬性，請添加相應的 getters 和 setters
    /*
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    */

    @Override
    public String toString() {
        return "CouponTypeVO{" +
                "coupId=" + coupId +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }
}
