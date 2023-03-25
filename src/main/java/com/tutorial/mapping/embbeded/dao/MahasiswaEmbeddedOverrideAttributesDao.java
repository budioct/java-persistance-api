package com.tutorial.mapping.embbeded.dao;

import com.tutorial.mapping.embbeded.entity.MahasiswaEmbeddedOverrideAttributes;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class MahasiswaEmbeddedOverrideAttributesDao implements CrudRepository<MahasiswaEmbeddedOverrideAttributes, Long> {

    private Session session;

    public MahasiswaEmbeddedOverrideAttributesDao(Session session) {
        this.session = session;
    }

    @Override
    public MahasiswaEmbeddedOverrideAttributes save(MahasiswaEmbeddedOverrideAttributes value) throws HibernateException {
        Long data = (Long) this.session.save(value);
        value.setId(data);
        return value;
    }

    @Override
    public MahasiswaEmbeddedOverrideAttributes update(MahasiswaEmbeddedOverrideAttributes value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MahasiswaEmbeddedOverrideAttributes> findById(Long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MahasiswaEmbeddedOverrideAttributes> findAll() {
        throw new UnsupportedOperationException();
    }
}
