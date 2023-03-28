package com.tutorial.query.mapping.onetomany.dao;

import com.tutorial.query.mapping.onetomany.entity.Employee;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class EmployeeDao implements CrudRepository<Employee, Long> {

    private Session session;

    public EmployeeDao(Session session) {
        this.session = session;
    }

    @Override
    public Employee save(Employee value) throws HibernateException {
        Long save = (Long) this.session.save(value);
        value.setId(save);
        return value;
    }

    @Override
    public Employee update(Employee value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Employee> findById(Long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Employee> findAll() {
        throw new UnsupportedOperationException();
    }
}
