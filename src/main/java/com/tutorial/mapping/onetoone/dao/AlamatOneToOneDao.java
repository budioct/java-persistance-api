package com.tutorial.mapping.onetoone.dao;

import com.tutorial.mapping.onetoone.entity.AlamatOneToOne;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class AlamatOneToOneDao implements CrudRepository<AlamatOneToOne, String> {

    private Session session;

    public AlamatOneToOneDao(Session session) {
        this.session = session;
    }

    @Override
    public AlamatOneToOne save(AlamatOneToOne value) throws HibernateException {
        String serializable = (String) this.session.save(value); // Serializable save(Object var1)
        value.setId(serializable);
        return value;
    }

    @Override
    public AlamatOneToOne update(AlamatOneToOne value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AlamatOneToOne> findById(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AlamatOneToOne> findAll() {
        throw new UnsupportedOperationException();
    }
}
