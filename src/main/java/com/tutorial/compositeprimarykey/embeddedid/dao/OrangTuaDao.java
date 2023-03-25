package com.tutorial.compositeprimarykey.embeddedid.dao;

import com.tutorial.compositeprimarykey.embeddedid.entity.OrangTua;
import com.tutorial.compositeprimarykey.entity.Anak;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class OrangTuaDao implements CrudRepository<OrangTua, Anak> {

    Session session;

    public OrangTuaDao(Session session) {
        this.session = session;
    }

    /**
     * OrangTua class yang Di Embedded id class anak.. @EmbeddedId
     * Anak class yang menyediakan Id .. @Embeddable
     */

    @Override
    public OrangTua save(OrangTua value) throws HibernateException {
        Anak primaryKey = (Anak) this.session.save(value); //  Serializable save(Object var1) // karna return Serializable // kita bisa convert ke turunan Object
        value.setId(primaryKey);
        return value;
    }

    @Override
    public OrangTua update(OrangTua value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Anak value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<OrangTua> findById(Anak value) {
        OrangTua orangTua = this.session.find(OrangTua.class, value);// <T> T find(Class<T> var1, Object var2) // menemukan dengan primary key
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        return orangTua != null ? Optional.of(orangTua) : Optional.empty();
    }

    @Override
    public List<OrangTua> findAll() {
        throw new UnsupportedOperationException();
    }
}
