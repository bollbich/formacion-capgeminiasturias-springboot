package com.formacionsprongboot.apirest.RRHH.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionsprongboot.apirest.RRHH.entity.Departamento;

@Repository
public interface DepartamentoDao extends CrudRepository<Departamento, Long> {

}
