package com.tutorial.mapping.onetomany.dao;

import com.tutorial.mapping.onetomany.entity.MahasiswaOneToMany;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MahasiswaOneToManyDao implements CrudRepository<MahasiswaOneToMany, String> {

    private Session session;

    public MahasiswaOneToManyDao(Session session) {
        this.session = session;
    }

    @Override
    public MahasiswaOneToMany save(MahasiswaOneToMany value) throws HibernateException {
        String data = (String) this.session.save(value); // Serializable save(Object var1) // kita bisa convert ke class Object
        value.setId(data);
        return value;
    }

    @Override
    public MahasiswaOneToMany update(MahasiswaOneToMany value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MahasiswaOneToMany> findById(String value) {
        MahasiswaOneToMany mahasiswaOneToMany = this.session.find(MahasiswaOneToMany.class, value);// <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return mahasiswaOneToMany != null ? Optional.of(mahasiswaOneToMany) : Optional.empty();
    }

    @Override
    public List<MahasiswaOneToMany> findAll() {
        throw new UnsupportedOperationException();
    }
}
