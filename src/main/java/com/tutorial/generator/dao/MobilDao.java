package com.tutorial.generator.dao;

import com.tutorial.generator.entity.Mobil;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MobilDao implements CrudRepository<Mobil, Long> {

    private Session session;

    public MobilDao(Session session) {
        this.session = session;
    }

    @Override
    public Mobil save(Mobil value) throws HibernateException {
        Long save = (Long) this.session.save(value); //  Serializable save(Object var1) // karna return Serializable // kita bisa convert ke turunan Object
        value.setId(save);
        return value;
    }

    @Override
    public Mobil update(Mobil value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public Optional<Mobil> findById(Long value) {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }

    @Override
    public List<Mobil> findAll() {
        throw new UnsupportedOperationException(); // supaya tidak digunakan
    }
}
