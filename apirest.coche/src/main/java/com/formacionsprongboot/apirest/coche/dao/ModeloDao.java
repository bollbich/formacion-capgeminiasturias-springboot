package com.formacionsprongboot.apirest.coche.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionsprongboot.apirest.coche.entity.Modelo;

@Repository
public interface ModeloDao extends CrudRepository<Modelo, Long>{

}
