package com.formacionsprongboot.apirest.productos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionsprongboot.apirest.productos.entity.Producto;
import com.formacionsprongboot.apirest.productos.entity.Region;

@Repository
public interface ProductoDao extends CrudRepository<Producto, Long>{

	@Query("from Region")
	public List<Region> findAllRegions();
}
