package com.HWday19.Dao;

import org.aspectj.lang.annotation.Before;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class playerJPA {
    EntityManagerFactory factory;

    @Before("")
    public void before(){
        factory = Persistence.createEntityManagerFactory("hibernateJPA");
    }

    

}
