package com.tutorial.mapping.manytomany.dao;

import com.tutorial.mapping.manytomany.entity.MataKuliahManyToMany;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class MataKuliahManyToManyDao implements CrudRepository<MataKuliahManyToMany, String> {

    private Session session;

    public MataKuliahManyToManyDao(Session session) {
        this.session = session;
    }

    @Override
    public MataKuliahManyToMany save(MataKuliahManyToMany value) throws HibernateException {
        String save = (String) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public MataKuliahManyToMany update(MataKuliahManyToMany value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(String value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MataKuliahManyToMany> findById(String value) {
        MataKuliahManyToMany mataKuliahManyToMany = this.session.find(MataKuliahManyToMany.class, value);  // <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return mataKuliahManyToMany != null ? Optional.of(mataKuliahManyToMany) : Optional.empty();
    }

    @Override
    public List<MataKuliahManyToMany> findAll() {
        throw new UnsupportedOperationException();
    }
}
