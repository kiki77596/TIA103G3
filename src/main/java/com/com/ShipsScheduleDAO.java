package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cruiseReservation.model.HibernateUtil;

import java.util.List;

public class ShipsScheduleDAO {

    public void insertShipsSchedule(ShipsScheduleVO shipSchedule) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(shipSchedule);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public ShipsScheduleVO selectShipsSchedule(int shipId) {
        ShipsScheduleVO shipSchedule = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            shipSchedule = session.get(ShipsScheduleVO.class, shipId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shipSchedule;
    }

    public List<ShipsScheduleVO> selectAllShipsSchedules() {
        List<ShipsScheduleVO> shipsSchedules = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            shipsSchedules = session.createQuery("from ShipsScheduleVO", ShipsScheduleVO.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shipsSchedules;
    }

    public void updateShipsSchedule(ShipsScheduleVO shipSchedule) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shipSchedule);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteShipsSchedule(int shipId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ShipsScheduleVO shipSchedule = session.get(ShipsScheduleVO.class, shipId);
            if (shipSchedule != null) {
                session.delete(shipSchedule);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	public List<ShipsScheduleVO> getAllShipsSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

	public ShipsScheduleVO findById(int shipId) {
		// TODO Auto-generated method stub
		return null;
	}
}
