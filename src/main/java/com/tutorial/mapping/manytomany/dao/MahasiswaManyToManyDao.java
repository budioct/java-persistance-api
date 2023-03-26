package com.tutorial.mapping.manytomany.dao;

import com.tutorial.mapping.manytomany.entity.MahasiswaManyToMany;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class MahasiswaManyToManyDao implements CrudRepository<MahasiswaManyToMany, String> {

    private Session session;

    public MahasiswaManyToManyDao(Session session) {
        this.session = session;
    }

    @Override
    public MahasiswaManyToMany save(MahasiswaManyToMany value) throws HibernateException {
        String save = (String) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public MahasiswaManyToMany update(MahasiswaManyToMany value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MahasiswaManyToMany> findById(String value) {
        MahasiswaManyToMany mahasiswaManyToMany = this.session.find(MahasiswaManyToMany.class, value); // <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return mahasiswaManyToMany != null ? Optional.of(mahasiswaManyToMany) : Optional.empty();
    }

    @Override
    public List<MahasiswaManyToMany> findAll() {
        throw new UnsupportedOperationException();
    }
}
