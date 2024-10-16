package com;

import com.cruiseReservation.model.CouponTypeVO;
import com.cruiseReservation.model.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CouponTypeDAO {

    public void insertCouponType(CouponTypeVO couponType) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(couponType);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public CouponTypeVO selectCouponType(int coupId) {
        CouponTypeVO couponType = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            couponType = session.get(CouponTypeVO.class, coupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return couponType;
    }

    public List<CouponTypeVO> selectAllCouponTypes() {
        List<CouponTypeVO> couponTypes = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            couponTypes = session.createQuery("from CouponTypeVO", CouponTypeVO.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return couponTypes;
    }

    public void updateCouponType(CouponTypeVO couponType) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(couponType);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteCouponType(int coupId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CouponTypeVO couponType = session.get(CouponTypeVO.class, coupId);
            if (couponType != null) {
                session.delete(couponType);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	public List<CouponTypeVO> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	public CouponTypeVO findById(Integer couponId) {
		// TODO Auto-generated method stub
		return null;
	}
}
