package com.tutorial.query.hql.dao;

import com.tutorial.mapping.enumeration.entity.EmployeeStatus;
import com.tutorial.mapping.manytoone.entity.KelasManyToOne;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class DistinctStatementDao {

    private EntityManager entityManager;

    private Session session;

    public DistinctStatementDao(Session session) {
        this.session = session;
    }

    public List<EmployeeStatus> findByDistictProjectSQL(){

        //language=HQL
        String hql = "select distinct status from EmployeeEnumOrdinal";
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<EmployeeStatus> query = this.session.createQuery(hql, EmployeeStatus.class);
        List<EmployeeStatus> employeeStatusList = query.getResultList();
        return employeeStatusList;
    }

    public List<KelasManyToOne> findByDistictEntityQuery(){

        //language=HQL
        String hql = "select distinct kls from MahasiswaManyToOne mhs join KelasManyToOne kls on (mhs.kelas = kls)";
        //String hql = "select distinct kls from MahasiswaManyToOne mhs left join KelasManyToOne kls on (mhs.kelas = kls)";
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<KelasManyToOne> query = this.session.createQuery(hql, KelasManyToOne.class);
        List<KelasManyToOne> kelasList = query.getResultList();
        return kelasList;
    }





}
