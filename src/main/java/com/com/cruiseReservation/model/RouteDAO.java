package com.cruiseReservation.model;

import com.cruiseReservation.model.RouteVO;
import com.cruiseReservation.model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@SuppressWarnings("unused")
public class RouteDAO {

    public void insertRoute(RouteVO route) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(route);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public RouteVO selectRoute(int routeId) {
        RouteVO route = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            route = session.get(RouteVO.class, routeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public List<RouteVO> selectAllRoutes() {
        List<RouteVO> routes = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            routes = session.createQuery("from RouteVO", RouteVO.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }

    public void updateRoute(RouteVO route) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(route);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteRoute(int routeId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            RouteVO route = session.get(RouteVO.class, routeId);
//            RouteVO route = new   RouteVO();
//            route.setRouteId(routeId);
            if (route != null) {
                session.delete(route);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	public void insert(RouteVO route) {
		// TODO Auto-generated method stub
		
	}
}
