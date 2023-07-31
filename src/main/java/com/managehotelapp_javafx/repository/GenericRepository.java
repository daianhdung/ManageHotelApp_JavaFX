package com.managehotelapp_javafx.repository;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

    List<T> query(String hql, Map<String, Object> parameters);

    int count(String hql, Map<String, Object> parameters);

    boolean insert(T t);

    boolean update(String hql, Map<String, Object> parameters);

    boolean delete(String hql, Map<String, Object> parameters);

    T findByIdEntity(Class<T> entityClass,int id);

    boolean update(T t);

    boolean save(T t);

    int saveAndReturnId(T t);
}
