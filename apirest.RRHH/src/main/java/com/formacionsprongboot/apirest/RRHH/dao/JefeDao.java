package com.formacionsprongboot.apirest.RRHH.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionsprongboot.apirest.RRHH.entity.Jefe;

@Repository
public interface JefeDao extends CrudRepository<Jefe, Long> {

}
