package com.tutorial.compositeprimarykey.IdClass.dao;

import com.tutorial.compositeprimarykey.IdClass.entity.KeyIdClass;
import com.tutorial.compositeprimarykey.IdClass.entity.MappingIdClass;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MappingIdClassDao implements CrudRepository<MappingIdClass, KeyIdClass> {

    private Session session;

    public MappingIdClassDao(Session session) {
        this.session = session;
    }

    @Override
    public MappingIdClass save(MappingIdClass value) throws HibernateException {
        KeyIdClass primaryKey = (KeyIdClass) this.session.save(value);
        value.setClassId(primaryKey.getClassId()); // void setClassId(final String classId) // String getClassId(),  primaryKey.getClassId()  supaya provide object primarykey retrun value sesuai request method
        value.setYear(primaryKey.getYear());
        return value;
    }

    @Override
    public MappingIdClass update(MappingIdClass value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(KeyIdClass value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MappingIdClass> findById(KeyIdClass value) {
        MappingIdClass mappingIdClass = this.session.find(MappingIdClass.class, value); // <T> T find(Class<T> var1, Object var2) // menemukan dengan primary key
        // static <T> Optional<T> of(T value) // return value yang tidak null yang diberikan oleh param
        // static <T> Optional<T> empty() // return value null
        return mappingIdClass != null ? Optional.of(mappingIdClass) : Optional.empty();

    }

    @Override
    public List<MappingIdClass> findAll() {
        throw new UnsupportedOperationException();
    }
}
