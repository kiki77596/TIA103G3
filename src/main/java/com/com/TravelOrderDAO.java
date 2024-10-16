package com;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cruiseReservation.model.HibernateUtil;

import java.util.List;

public class TravelOrderDAO {
    
    // 插入新的旅遊訂單
    public void insert(TravelOrderVO travelOrder) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(travelOrder);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            // 您可以在這裡拋出自定義異常或進行其他處理
        }
    }
    
    // 根據 ID 查詢旅遊訂單
    public TravelOrderVO findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TravelOrderVO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 更新旅遊訂單
    public void update(TravelOrderVO travelOrder) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(travelOrder);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            // 您可以在這裡拋出自定義異常或進行其他處理
        }
    }
    
    // 刪除旅遊訂單
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TravelOrderVO travelOrder = session.get(TravelOrderVO.class, id);
            if (travelOrder != null) {
                session.delete(travelOrder);
                transaction.commit();
            } else {
                System.out.println("旅遊訂單 ID " + id + " 不存在，無法刪除。");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            // 您可以在這裡拋出自定義異常或進行其他處理
        }
    }
    
    // 獲取所有旅遊訂單
    @SuppressWarnings("unchecked")
    public List<TravelOrderVO> getAllTravelOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<TravelOrderVO> query = session.createQuery("FROM TravelOrderVO");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
