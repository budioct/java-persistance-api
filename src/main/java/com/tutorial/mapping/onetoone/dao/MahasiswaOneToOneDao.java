package com.tutorial.mapping.onetoone.dao;

import com.tutorial.mapping.onetoone.entity.MahasiswaOneToOne;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class MahasiswaOneToOneDao implements CrudRepository<MahasiswaOneToOne, String> {

    private Session session;

    public MahasiswaOneToOneDao(Session session) {
        this.session = session;
    }

    @Override
    public MahasiswaOneToOne save(MahasiswaOneToOne value) throws HibernateException {
        String save = (String) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public MahasiswaOneToOne update(MahasiswaOneToOne value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MahasiswaOneToOne> findById(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MahasiswaOneToOne> findAll() {
        throw new UnsupportedOperationException();
    }
}
