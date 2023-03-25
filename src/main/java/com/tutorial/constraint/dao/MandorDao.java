package com.tutorial.constraint.dao;

import com.tutorial.constraint.entity.Mandor;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MandorDao implements CrudRepository<Mandor, String> {

    private Session session;

    public MandorDao(Session session) {
        this.session = session;
    }

    @Override
    public Mandor save(Mandor value) throws HibernateException {
        String save = (String) this.session.save(value); //  Serializable save(Object var1) // karna return Serializable // kita bisa convert ke turunan Object
        value.setId(save);
        return value;
    }

    @Override
    public Mandor update(Mandor value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public Optional<Mandor> findById(String value) {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public List<Mandor> findAll() {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }
}
