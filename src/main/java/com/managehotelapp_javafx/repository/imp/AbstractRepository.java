package com.managehotelapp_javafx.repository.imp;

import com.managehotelapp_javafx.config.ConnectDB;
import com.managehotelapp_javafx.repository.GenericRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class AbstractRepository<T> implements GenericRepository<T> {

    private void setParameter(Query query, Map<String, Object> parameters) {
        try {
            for(var item : parameters.entrySet()){
                query.setParameter(item.getKey(), item.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error setParameter");
        }
    }
    @Override
    public List<T> query(String hql, Map<String, Object> parameters) {
        Session session = ConnectDB.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        if(parameters != null){
            setParameter(query, parameters);
        }
        try{
            List<T> result = query.list();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public int count(String hql, Map<String, Object> parameters) {
        Session session = ConnectDB.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        if(parameters != null){
            setParameter(query, parameters);
        }
        try{
           int count = (int) query.uniqueResult();
           return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean insert(T t) {
        Session session = ConnectDB.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(String hql, Map<String, Object> parameters) {
        Session session = ConnectDB.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(hql);
            if(parameters != null){
                setParameter(query, parameters);
            }
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String hql, Map<String, Object> parameters) {
        Session session = ConnectDB.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(hql);
            if(parameters != null){
                setParameter(query, parameters);
            }
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
