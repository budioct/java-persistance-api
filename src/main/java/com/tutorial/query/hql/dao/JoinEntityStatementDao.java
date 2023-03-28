package com.tutorial.query.hql.dao;

import com.tutorial.mapping.onetoone.entity.AlamatOneToOne;
import com.tutorial.mapping.onetoone.entity.MahasiswaOneToOne;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class JoinEntityStatementDao {

    /**
     * Hibernate org.hibernate.Session memanfaatkan interface org.hibernate.Query untuk JPA (Java Persistance API)
     * untuk bisa menggunakan interface EntityManager // javax.persistence.Query
     */

    private Session session;

    public JoinEntityStatementDao(Session session) {
        this.session = session;
    }

    // Join Table implicit
    public List<MahasiswaOneToOne> findByProvinsiWithImplicitJoin(String provinsi) {

        //language=HQL
        String hql = "from MahasiswaOneToOne mhs where mhs.alamat.provinsi = :namaProvinsi";
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<MahasiswaOneToOne> query = this.session.createQuery(hql, MahasiswaOneToOne.class)
                .setParameter("namaProvinsi", provinsi);
        List<MahasiswaOneToOne> data = query.getResultList();
        return data;
    }

    // Join Table explicit (With dan On)
    public List<MahasiswaOneToOne> findByProvinsiWithExplicitJoinOn(String provinsi) {

        //language=HQL
        String hql = "select mhs from MahasiswaOneToOne mhs join AlamatOneToOne alamat on (mhs.alamat = alamat) where alamat.provinsi = :namaProvinsi";
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<MahasiswaOneToOne> query = this.session.createQuery(hql, MahasiswaOneToOne.class)
                .setParameter("namaProvinsi", provinsi);
        List<MahasiswaOneToOne> data = query.getResultList();
        return data;
    }


}
