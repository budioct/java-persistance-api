package com.tutorial.mapping.onetomany.dao;

import com.tutorial.mapping.onetomany.entity.AlamatOneToMany;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AlamatOneToManyDao implements CrudRepository<AlamatOneToMany, String> {

    private Session session;

    public AlamatOneToManyDao(Session session) {
        this.session = session;
    }

    @Override
    public AlamatOneToMany save(AlamatOneToMany value) throws HibernateException {
        String data = (String) this.session.save(value); // Serializable save(Object var1) // kita bisa convert ke class Object
        value.setId(data);
        return value;
    }

    @Override
    public AlamatOneToMany update(AlamatOneToMany value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AlamatOneToMany> findById(String value) {
        AlamatOneToMany alamatOneToMany = this.session.find(AlamatOneToMany.class, value);// <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return alamatOneToMany != null ? Optional.of(alamatOneToMany) : Optional.empty();
    }

    @Override
    public List<AlamatOneToMany> findAll() {
        throw new UnsupportedOperationException();
    }
}
