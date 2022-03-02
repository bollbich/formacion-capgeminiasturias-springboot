package com.formacionsprongboot.apirest.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionsprongboot.apirest.productos.dao.ProductoDao;
import com.formacionsprongboot.apirest.productos.entity.Producto;
import com.formacionsprongboot.apirest.productos.entity.Region;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	ProductoDao AccesoDb;
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> ListarTodosProductos() {

		return (List<Producto>) AccesoDb.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Producto FinById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Producto save(Producto producto) {
		
		return AccesoDb.save(producto);
	}

	@Override
	@Transactional(readOnly=true)
	public void Delete(Long id) {

		AccesoDb.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegions() {

		return AccesoDb.findAllRegions();
	}

}
