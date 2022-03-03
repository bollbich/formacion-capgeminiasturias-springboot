package com.formacionsprongboot.apirest.RRHH.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDao extends CrudRepository<Empleado, Long> {

}
