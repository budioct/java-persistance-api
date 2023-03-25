package com.tutorial.repository;

import org.hibernate.HibernateException;

public interface CrudRepository<T, PK> extends ReadRepository<T, PK> {

    T save(T value) throws HibernateException;

    T update(T value) throws HibernateException;

    boolean removeById(PK value) throws HibernateException;

}
