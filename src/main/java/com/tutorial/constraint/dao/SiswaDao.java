package com.tutorial.constraint.dao;

import com.tutorial.constraint.entity.Siswa;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class SiswaDao implements CrudRepository<Siswa, String> {

    private Session session;

    public SiswaDao(Session session) {
        this.session = session;
    }

    @Override
    public Siswa save(Siswa value) throws HibernateException {
        String save = (String) this.session.save(value); //  Serializable save(Object var1) // karna return Serializable // kita bisa convert ke turunan Object
        value.setId(save);
        return value;
    }

    @Override
    public Siswa update(Siswa value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public Optional<Siswa> findById(String value) {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public List<Siswa> findAll() {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }
}
