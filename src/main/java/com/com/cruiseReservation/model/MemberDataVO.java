package com.cruiseReservation.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MemberDataVO {

    public void insertMemberData(MemberDataVO memberData) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(memberData);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public MemberDataVO selectMemberData(int memberId) {
        MemberDataVO memberData = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            memberData = session.get(MemberDataVO.class, memberId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberData;
    }

    public List<MemberDataVO> selectAllMemberData() {
        List<MemberDataVO> memberDataList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            memberDataList = session.createQuery("from MemberDataVO", MemberDataVO.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberDataList;
    }

    public void updateMemberData(MemberDataVO memberData) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(memberData);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteMemberData(int memberId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MemberDataVO memberData = session.get(MemberDataVO.class, memberId);
            if (memberData != null) {
                session.delete(memberData);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	public List<MemberDataVO> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberDataVO findById(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
