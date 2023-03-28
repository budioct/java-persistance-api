package com.tutorial.query.mapping.onetomany.dao;

import com.tutorial.query.mapping.onetomany.entity.Manager;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ManagerDao implements CrudRepository<Manager, Long> {

    private Session session;

    public ManagerDao(Session session) {
        this.session = session;
    }

    @Override
    public Manager save(Manager value) throws HibernateException {
        Long save = (Long) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public Manager update(Manager value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Manager> findById(Long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Manager> findAll() {
        throw new UnsupportedOperationException();
    }
}
