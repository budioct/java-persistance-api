package com.tutorial.query.hql.dao;

import com.tutorial.query.dto.ArithmeticModelDTO;
import com.tutorial.query.mapping.onetomany.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ArithmeticHQLDao {

    /**
     * Hibernate org.hibernate.Session memanfaatkan interface org.hibernate.Query untuk JPA (Java Persistance API)
     * untuk bisa menggunakan interface EntityManager // javax.persistence.Query
     */

    private Session session;

    public ArithmeticHQLDao(Session session) {
        this.session = session;
    }

    public List<ArithmeticModelDTO> selectWithArithmeticModel() {
        //language=HQL
        String hql = "select new com.tutorial.query.dto.ArithmeticModelDTO(id, fullName, salary * 12L, salary + 10000L) from Employee"; // kalau compiler merah tidak apa apa
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<ArithmeticModelDTO> query = this.session.createQuery(hql, ArithmeticModelDTO.class);
        List<ArithmeticModelDTO> dataArithmetic = query.getResultList();
        return dataArithmetic;
    }

    public List<ArithmeticModelDTO> selectWithArithmeticModelParameter(Long idEmployee) {
        //language=HQL
        String hql = "select new com.tutorial.query.dto.ArithmeticModelDTO(id, fullName, salary * 12L, salary + 10000L) from Employee where id = : idEmployee"; // kalau compiler merah tidak apa apa
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<ArithmeticModelDTO> query = this.session.createQuery(hql, ArithmeticModelDTO.class)
                .setParameter("idEmployee", idEmployee);
        List<ArithmeticModelDTO> dataArithmetic = query.getResultList();
        return dataArithmetic;
    }

    public List<Long> hitungTotalSalarySemuaEmployeeDalamSetahun() {

        //language=HQL
        String hql = "select sum(salary * 12) from Employee";
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<Long> query = this.session.createQuery(hql, Long.class);
        List<Long> data = query.getResultList();
        return data;
    }

    public List<Employee> cariKaryawanYangSalaryDalamSetahunDiatas(Long salary){

        //language=HQL
        String hql = "from Employee where (salary * 12) >= : salaryCompare";
        // Query<T> createQuery(String var1, Class<T> var2) // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // List<R> getResultList() // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<Employee> query = this.session.createQuery(hql, Employee.class)
                .setParameter("salaryCompare", salary);
        List<Employee> data = query.getResultList();
        return data;
    }



}
