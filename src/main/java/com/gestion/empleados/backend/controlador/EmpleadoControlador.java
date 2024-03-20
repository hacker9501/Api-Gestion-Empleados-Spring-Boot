package com.gestion.empleados.backend.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empleados.backend.repositorio.EmpleadoRepositorio;
import com.gestion.empleados.backend.excepciones.ResourceNotFoundException;
import com.gestion.empleados.backend.modelo.Empleado;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins= "http://localhost:4200/")
public class EmpleadoControlador {

	//inyecta al repository
	@Autowired
	private EmpleadoRepositorio repository;
	
	//este metodo sirve para listar todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> listarTodosLosEmpleados(){
		return repository.findAll();
	}
	
	//este metodo sirve para guardar un empleado
	@PostMapping("/empleados")
	public Empleado guadarEmpleado(@RequestBody Empleado empleado) {
		return repository.save(empleado);
	}
	
	//este metodo sirve para buscar un empleado
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Empleado> obtenerEmpleadorPorId(@PathVariable Long id){
		Empleado empleado = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		return ResponseEntity.ok(empleado);
	}
	
	//este metodo actualiza el empleado
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id,@RequestBody Empleado detalleEmpleado){
		Empleado empleado = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
		empleado.setNombre(detalleEmpleado.getNombre());
		empleado.setApellido(detalleEmpleado.getApellido());
		empleado.setEmail(detalleEmpleado.getEmail());
		
		Empleado empleadoActualizado = repository.save(empleado);
		
		return ResponseEntity.ok(empleadoActualizado);
		
	}
	
	//este metodo sirve para eliminar un empleado
		@DeleteMapping("/empleados/{id}")
		public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
			Empleado empleado = repository.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
			
			repository.delete(empleado);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar",Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
	    }
		
		
	
}
