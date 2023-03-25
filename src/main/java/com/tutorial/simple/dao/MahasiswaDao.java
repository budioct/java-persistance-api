package com.tutorial.simple.dao;

import com.tutorial.simple.master.Mahasiswa;
import com.tutorial.repository.CrudRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MahasiswaDao implements CrudRepository<Mahasiswa, Long> {

    // kita cuman perluu Session

    private Session session; // untuk membuka koneksi ORM dari object java

    // Dependncy Injection
    public MahasiswaDao(Session session) {
        this.session = session;
    }

    @Override
    public Mahasiswa save(Mahasiswa value) throws HibernateException {
        Long returnKey = (Long) this.session.save(value);// Serializable save(Object var1) // karena return Serializable kita bisa return number
        value.setId(returnKey); // enkapsulapsi dari object Mahasiswa
        return value;
    }

    @Override
    public Mahasiswa update(Mahasiswa value) throws HibernateException {
        this.session.update(value);
        return value;
    }

    public boolean removeBy(Mahasiswa value) throws HibernateException {
        this.session.remove(value);
        return true;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
       this.session.remove(value);
        return true;
    }

    @Override
    public Optional<Mahasiswa> findById(Long value) {
        Mahasiswa mahasiswa = this.session.find(Mahasiswa.class, value);
        return mahasiswa != null? Optional.of(mahasiswa) : Optional.empty(); // cek ternary
    }

    @Override
    public List<Mahasiswa> findAll() {
        throw new UnsupportedOperationException();
    }

}
