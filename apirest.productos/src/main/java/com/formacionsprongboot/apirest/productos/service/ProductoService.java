package com.formacionsprongboot.apirest.productos.service;

import java.util.List;

import com.formacionsprongboot.apirest.productos.entity.Producto;
import com.formacionsprongboot.apirest.productos.entity.Region;


public interface ProductoService {
		
		public List<Producto> ListarTodosProductos();
		
		public Producto FinById(Long id);
		
		public Producto save(Producto producto);
		
		public void Delete(Long id);
		
		public List<Region> findAllRegions();

	

}
