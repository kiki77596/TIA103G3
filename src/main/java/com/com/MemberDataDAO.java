package com;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cruiseReservation.model.HibernateUtil;
import com.cruiseReservation.model.MemberDataVO;

import java.util.List;

public class MemberDataDAO {
    public void insertMember(MemberDataVO member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public MemberDataVO selectMember(int memberId) {
        MemberDataVO member = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            member = session.get(MemberDataVO.class, memberId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }
    @SuppressWarnings("unchecked")
    public List<MemberDataVO> selectAllMembers() {
        List<MemberDataVO> members = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MemberDataVO> query = session.createQuery("FROM MemberDataVO");
            members = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return members;
    }
    public void updateMember(MemberDataVO member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public void deleteMember(int memberId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MemberDataVO member = session.get(MemberDataVO.class, memberId);
            if (member != null) {
                session.delete(member);
                System.out.println("��撌脣�: " + memberId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            // ��隞交���閬��摰儔靘��
        }
    }
}
