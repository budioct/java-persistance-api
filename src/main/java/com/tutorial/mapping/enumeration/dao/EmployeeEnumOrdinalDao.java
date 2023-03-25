package com.tutorial.mapping.enumeration.dao;

import com.tutorial.mapping.enumeration.entity.EmployeeEnumOrdinal;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class EmployeeEnumOrdinalDao implements CrudRepository<EmployeeEnumOrdinal, Long> {

    private Session session;

    public EmployeeEnumOrdinalDao(Session session) {
        this.session = session;
    }

    @Override
    public EmployeeEnumOrdinal save(EmployeeEnumOrdinal value) throws HibernateException {
        Long returnKey = (Long) this.session.save(value); // Serializable save(Object var1) // karna return Serializable kita bisa merubah return turunan Object
        value.setId(returnKey); // void setId(Long data) // set param sesuai request nya
        return value;
    }

    @Override
    public EmployeeEnumOrdinal update(EmployeeEnumOrdinal value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<EmployeeEnumOrdinal> findById(Long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<EmployeeEnumOrdinal> findAll() {
        throw new UnsupportedOperationException();
    }
}
