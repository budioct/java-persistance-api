package com.tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntityExample {

    /**
     * ini adalah class entity, model, java bean, POJO, Domain
     * setelah class dibuat kita harus registry ke Hibernate, di file hibernate.cfg.xml. harus lengkap dengan package2 nya
     */

    @Id
    private String id;
    private String nama;

    private Long nilai;



}
