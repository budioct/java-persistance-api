package com.tutorial.mapping.manytoone.dao;

import com.tutorial.mapping.manytoone.entity.KelasManyToOne;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class KelasManyToOneDao implements CrudRepository<KelasManyToOne, String> {

    private Session session;

    public KelasManyToOneDao(Session session) {
        this.session = session;
    }

    @Override
    public KelasManyToOne save(KelasManyToOne value) throws HibernateException {
        String save = (String) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public KelasManyToOne update(KelasManyToOne value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<KelasManyToOne> findById(String value) {
        KelasManyToOne kelasManyToOne = this.session.find(KelasManyToOne.class, value); // <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return kelasManyToOne != null ? Optional.of(kelasManyToOne) : Optional.empty();
    }

    @Override
    public List<KelasManyToOne> findAll() {
        throw new UnsupportedOperationException();
    }
}
