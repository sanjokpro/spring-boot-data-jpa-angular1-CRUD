package com.sanjok.springbootdataangular1crud.service;


import java.util.List;

public interface BaseService<ENTITY extends Object, IDENTITY extends Long> {
    List<ENTITY> findAll();

    ENTITY findById(IDENTITY identity) throws Exception;

    ENTITY insert(ENTITY entity);

    ENTITY update(ENTITY entity) throws Exception;

    ENTITY deleteById(IDENTITY identity) throws Exception;
}
