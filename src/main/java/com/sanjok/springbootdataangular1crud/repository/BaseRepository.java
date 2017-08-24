package com.sanjok.springbootdataangular1crud.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<Entity,ID extends  Serializable>  extends Repository<Entity,ID> {
    void delete(Entity deleted);
     List<Entity> findAll();
    Optional<Entity> findOne(ID id);
     Entity save(Entity saved);

}
