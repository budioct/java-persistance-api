package com.tutorial.mapping.embbeded.dao;

import com.tutorial.mapping.embbeded.entity.MahasiswaEmbedded;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MahasiswaEmbeddedDao implements CrudRepository<MahasiswaEmbedded, Long> {

    private Session session;

    public MahasiswaEmbeddedDao(Session session) {
        this.session = session;
    }

    @Override
    public MahasiswaEmbedded save(MahasiswaEmbedded value) throws HibernateException {
        Long data = (Long) this.session.save(value);
        value.setId(data);
        return value;
    }

    @Override
    public MahasiswaEmbedded update(MahasiswaEmbedded value) throws HibernateException {
        this.session.update(value);
        return value;
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MahasiswaEmbedded> findById(Long value) {
        MahasiswaEmbedded embedded = this.session.find(MahasiswaEmbedded.class, value);// <T> T find(Class<T> var1, Object var2)
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        // <T> Optional<T> ofNullable(T value) // return value yang diberikan, jika tidak null
        return embedded != null ? Optional.of(embedded) : Optional.empty();
    }

    @Override
    public List<MahasiswaEmbedded> findAll() {
        throw new UnsupportedOperationException();
    }
}
