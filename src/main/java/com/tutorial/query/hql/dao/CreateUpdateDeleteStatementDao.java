package com.tutorial.query.hql.dao;

import com.tutorial.repository.CrudRepository;
import com.tutorial.simple.master.Mahasiswa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class CreateUpdateDeleteStatementDao implements CrudRepository<Mahasiswa, Long> {

    /**
     * Hibernate org.hibernate.Session memanfaatkan interface org.hibernate.Query untuk JPA (Java Persistance API)
     * untuk bisa menggunakan interface EntityManager // javax.persistence.Query
     */

    private static final Logger log = LoggerFactory.getLogger(CreateUpdateDeleteStatementDao.class);

    private Session session;

    public CreateUpdateDeleteStatementDao(Session session) {
        this.session = session;
    }

    @Override
    public Mahasiswa save(Mahasiswa value) throws HibernateException {
        Long save = (Long) this.session.save(value); // Serializable save(Object var1) // bisa mengenal object. kita bisa ubah menjadi Object
        value.setId(save);
        return value;

    }

    @Override
    public Mahasiswa update(Mahasiswa value) throws HibernateException {
        //language=HQL
        String hql = "update Mahasiswa set nama = :nama, thnMasuk = :tahunMasuk, active = :status, biodata = :bio where id = :id";
        // Query createQuery(String var1) // Buat instance JPA. // membaca HQL // tidak perlu menambahkan reflection Class<T>. karena tidak retrun object
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // int executeUpdate() // eksekusi Jalankan pernyataan pembaruan atau hapus. // return number
        int data = this.session.createQuery(hql)
                .setParameter("id", value.getId())
                .setParameter("status", value.getActive())
                .setParameter("nama", value.getNama())
                .setParameter("tahunMasuk", value.getThnMasuk())
                .setParameter("bio", value.getBiodata())
                .executeUpdate();
        log.info("updatate rows in mahasiswa= {}", data);
        return value;

    }

    @Override
    public boolean removeById(Long value) throws HibernateException {
        //language=HQL
        String hql = "delete from Mahasiswa where id = :id";
        // Query createQuery(String var1) // Buat instance JPA. // membaca HQL // tidak perlu menambahkan reflection Class<T>. karena tidak retrun object
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // int executeUpdate() // eksekusi Jalankan pernyataan pembaruan atau hapus. // return number
        int delete = this.session.createQuery(hql).setParameter("id", value).executeUpdate();
        return delete >= 1;

    }

    @Override
    public Optional<Mahasiswa> findById(Long value) {
        try {
            //language=HQL
            String hql = "from Mahasiswa where id = :id";
            // <T> org.hibernate.query.Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
            // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
            // R getSingleResult() // Jalankan kueri SELECT yang mengembalikan satu hasil yang tidak diketik.
            Query<Mahasiswa> query = this.session.createQuery(hql, Mahasiswa.class).setParameter("id", value);
            Mahasiswa result = query.getSingleResult();
            return result != null ? Optional.of(result) : Optional.empty();

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Mahasiswa> findAll() {

        //language=HQL
        String hql = "from Mahasiswa ";
        // <T> org.hibernate.query.Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<Mahasiswa> query = this.session.createQuery(hql, Mahasiswa.class);
        List<Mahasiswa> resultList = query.getResultList();
        return resultList;

    }
}
