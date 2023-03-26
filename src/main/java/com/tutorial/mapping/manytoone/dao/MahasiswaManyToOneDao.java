package com.tutorial.mapping.manytoone.dao;

import com.tutorial.mapping.manytoone.entity.MahasiswaManyToOne;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class MahasiswaManyToOneDao implements CrudRepository<MahasiswaManyToOne, String> {

    private Session session;

    public MahasiswaManyToOneDao(Session session) {
        this.session = session;
    }

    @Override
    public MahasiswaManyToOne save(MahasiswaManyToOne value) throws HibernateException {
        String save = (String) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public MahasiswaManyToOne update(MahasiswaManyToOne value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MahasiswaManyToOne> findById(String value) {
        MahasiswaManyToOne mahasiswaManyToOne = this.session.find(MahasiswaManyToOne.class, value); // <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return mahasiswaManyToOne != null ? Optional.of(mahasiswaManyToOne) : Optional.empty();
    }

    @Override
    public List<MahasiswaManyToOne> findAll() {
        throw new UnsupportedOperationException();
    }
}
