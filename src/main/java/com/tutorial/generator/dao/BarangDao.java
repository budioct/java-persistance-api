package com.tutorial.generator.dao;

import com.tutorial.generator.entity.Barang;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BarangDao implements CrudRepository<Barang, String> {

    private Session session;

    public BarangDao(Session session) {
        this.session = session;
    }

    @Override
    public Barang save(Barang value) throws HibernateException {
        String save = (String) this.session.save(value); //  Serializable save(Object var1) // karna return Serializable // kita bisa convert ke turunan Object
        value.setId(save);
        return value;
    }

    @Override
    public Barang update(Barang value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public Optional<Barang> findById(String value) {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public List<Barang> findAll() {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }
}
