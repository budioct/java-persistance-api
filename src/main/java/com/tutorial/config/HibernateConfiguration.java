package com.tutorial.config;

import com.tutorial.compositeprimarykey.IdClass.entity.MappingIdClass;
import com.tutorial.compositeprimarykey.embeddedid.entity.OrangTua;
import com.tutorial.generator.entity.Barang;
import com.tutorial.generator.entity.Mobil;
import com.tutorial.constraint.entity.Mandor;
import com.tutorial.constraint.entity.Siswa;
import com.tutorial.mapping.embbeded.entity.MahasiswaEmbedded;
import com.tutorial.mapping.embbeded.entity.MahasiswaEmbeddedOverrideAttributes;
import com.tutorial.mapping.enumeration.entity.EmployeeEnumOrdinal;
import com.tutorial.mapping.manytomany.entity.MahasiswaManyToMany;
import com.tutorial.mapping.manytomany.entity.MataKuliahManyToMany;
import com.tutorial.mapping.manytoone.entity.KelasManyToOne;
import com.tutorial.mapping.manytoone.entity.MahasiswaManyToOne;
import com.tutorial.mapping.onetomany.entity.AlamatOneToMany;
import com.tutorial.mapping.onetomany.entity.MahasiswaOneToMany;
import com.tutorial.mapping.onetoone.entity.AlamatOneToOne;
import com.tutorial.mapping.onetoone.entity.MahasiswaOneToOne;
import com.tutorial.simple.master.Mahasiswa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfiguration {

    private static final SessionFactory ourSessionFactory; // sebagai driver // Representasi thread-safe (dan tidak dapat diubah) dari pemetaan model domain aplikasi ke database.

    // block static{}: ketika object di deklarasi, di eksekusi selai ketika object pertama dari kelas dibuat
    static {

        // StandardServiceRegistry // kontrak dari registry
        // StandardServiceRegistryBuilder() // pembuat registry
        // StandardServiceRegistryBuilder configure() // akan registry hasil config hibernate yang sudah di set di file hibernate.cfg.xml
        // StandardServiceRegistry build() // eksekusi kontrak registry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // pengaturan konfigurasi dari hibernate.cfg.xml
                .build();

        try {
            MetadataSources metadataSources = new MetadataSources(registry); // MetadataSources // kontrak mendaftarkan setiap sumber: config hibernate, class entity (POJO)

            // registry class Entity, supaya bisa di generate oleh hibernate
            // MetadataSources addAnnotatedClass(Class annotatedClass) // registry class entity // dengan java reflection Class<T>
            metadataSources
                    .addAnnotatedClass(Mahasiswa.class)
                    .addAnnotatedClass(Siswa.class)
                    .addAnnotatedClass(Mandor.class)
                    .addAnnotatedClass(Mobil.class)
                    .addAnnotatedClass(Barang.class)
                    .addAnnotatedClass(OrangTua.class)
                    .addAnnotatedClass(MappingIdClass.class)
                    .addAnnotatedClass(EmployeeEnumOrdinal.class)
                    .addAnnotatedClass(MahasiswaEmbedded.class)
                    .addAnnotatedClass(MahasiswaEmbeddedOverrideAttributes.class)
                    .addAnnotatedClass(AlamatOneToOne.class)
                    .addAnnotatedClass(MahasiswaOneToOne.class)
                    .addAnnotatedClass(AlamatOneToMany.class)
                    .addAnnotatedClass(MahasiswaOneToMany.class)
                    .addAnnotatedClass(KelasManyToOne.class)
                    .addAnnotatedClass(MahasiswaManyToOne.class)
                    .addAnnotatedClass(MataKuliahManyToMany.class)
                    .addAnnotatedClass(MahasiswaManyToMany.class);

            // Metadata buildMetadata() // menentukan mapping antara class dan table baik menggunakan XML atau annotation dalam kode
            // SessionFactory buildSessionFactory() // bertangung jawab atas pembuatan object Session
            ourSessionFactory = metadataSources
                    .buildMetadata()
                    .buildSessionFactory(); // SessionFactory buildSessionFactory() // hasil dari kontrak MetadataSources akan return SessionFactory, supaya bisa di mapping ke DBMS

        } catch (Throwable throwable) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError(throwable);
        }
    }

    // sebagai connection // Sessionmembungkus JDBC java.sql.Connection dan bertindak sebagai pabrik misalnya org.hibernate.Transaction
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession(); // Session openSession() // setiap permintaan basis data Session Siap dibuka
    }

}
