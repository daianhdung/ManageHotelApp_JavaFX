package com.managehotelapp_javafx.repository;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

    List<T> query(String hql, Map<String, Object> parameters);

    boolean insert(T t);

    boolean update(String hql, Map<String, Object> parameters);

    boolean delete(String hql, Map<String, Object> parameters);
}
