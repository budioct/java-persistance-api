package com.tutorial.query.hql.dao;

import com.tutorial.repository.ReadRepository;
import com.tutorial.simple.master.Mahasiswa;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class SelectStatementHQLDao implements ReadRepository<Mahasiswa, Long> {

    /**
     * Hibernate org.hibernate.Session memanfaatkan interface org.hibernate.Query untuk JPA (Java Persistance API)
     * untuk bisa menggunakan interface EntityManager // javax.persistence.Query
     */

    private Session session;

    public SelectStatementHQLDao(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Mahasiswa> findById(Long value) {
        /**
         * klik alt + Enter. lalu enter inject languange or reference. lalu cari hibernate.. nanti akan muncul semua object entity yang menggunakan hibernate
         * jika nama entity ada yang sama kita harus menulis referencen class dengan detail beserta packagenya contoh: com.tutorial.simple.master.Mahasiswa
         * jika kita ingin mendapatkan data berdasarkan id. penulisan HQL:
         * satu parameter: "from ObjectEntityReference where id = ?1"
         * dua parameter: "from ObjectEntityReference where id = ?1 and nama = ?2"
         */

        //language=HQL
        String hql = "from com.tutorial.simple.master.Mahasiswa where id = ?1 "; // kita tidak perlu tulis SQL sunguhan.. tinggal ambil referensi dari object entity
        try {
            // Query createQuery(String var1) // membaca HQL // tidak perlu menambahkan reflection Class<T>. karena tidak retrun object
            // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
            // R getSingleResult() // Jalankan kueri SELECT yang mengembalikan satu hasil yang tidak diketik.
            Query<Mahasiswa> query = this.session.createQuery(hql, Mahasiswa.class); // <T> org.hibernate.query.Query<T> createQuery(String var1, Class<T> var2) // return generic Query<T>
            query.setParameter(1, value); // Query<R> setParameter(int var1, Object var2) // binding parameter query dengan parameter
            Mahasiswa result = query.getSingleResult(); // R getSingleResult() // query akan return Type return Object
            return result != null ? Optional.of(result) : Optional.empty(); // ternary operator

        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Mahasiswa> findAll() {
        //language=HQL
        String hql = "from  com.tutorial.simple.master.Mahasiswa";
        // Query<T> createQuery(String var1, Class<T> var2) //  // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // List<R> getResultList() // // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<Mahasiswa> query = this.session.createQuery(hql, Mahasiswa.class); // <T> org.hibernate.query.Query<T> createQuery(String var1, Class<T> var2) // return generic Query<T>
        return query.getResultList(); // List<R> getResultList()
    }

    public Optional<Mahasiswa> findByNim(String nim) {
        try {
            //language=HQL
            String hql = "from Mahasiswa where nim = :nimMahasiswa";
            // Query<T> createQuery(String var1, Class<T> var2) //  // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
            // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
            // R getSingleResult() // Jalankan kueri SELECT yang mengembalikan satu hasil yang tidak diketik.
            Query<Mahasiswa> query = this.session.createQuery(hql, Mahasiswa.class)
                    .setParameter("nimMahasiswa", nim);
            Mahasiswa data = query.getSingleResult();
            return data != null ? Optional.of(data) : Optional.empty();
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Mahasiswa> findByNamaOrTahunMasuk(String nama, Integer tahun) {
        //language=HQL
        String hql = "from Mahasiswa where nama = :namaMahasiswa or thnMasuk = :tahunMasuk";
        // Query<T> createQuery(String var1, Class<T> var2) //  // buat instance query yang diketik untuk string query  HQL/JPQL yang diberikan
        // Query<R> setParameter(String var1, Object var2) // instance, binding namedParameter hql dan parameter
        // List<R> getResultList() // // Jalankan kueri SELECT dan kembalikan hasil kueri sebagai Daftar yang tidak diketik.
        Query<Mahasiswa> query = this.session.createQuery(hql, Mahasiswa.class)
                .setParameter("namaMahasiswa", nama)
                .setParameter("tahunMasuk", tahun);
        return query.getResultList();

    }


}
