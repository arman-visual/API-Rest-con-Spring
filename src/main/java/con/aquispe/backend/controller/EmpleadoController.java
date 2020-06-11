package con.aquispe.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import con.aquispe.backend.models.entity.Empleado;
import con.aquispe.backend.service.IEmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Empleado>findAll() {
		return empleadoService.findAll();
	}
	
	@GetMapping("/empleado/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Empleado getEmpleadoById(@PathVariable Long id) {
		Optional<Empleado> empleado = empleadoService.findById(id);
		if(empleado.isPresent()) {
			return empleado.get();
		}else{
			throw new EntityNotFoundException(String.format("No existe empleado con id %d", id));
		}
	}
	
	@PutMapping("/actualizar")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado update(@RequestBody Empleado empleado) {
		return empleadoService.save(empleado);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado crear(@RequestBody Empleado empleado) {
		return empleadoService.save(empleado);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		empleadoService.deleteById(id);
	}
	
	@GetMapping("/buscar/{documento}")
	@ResponseStatus(HttpStatus.OK)
	public Empleado findByDocumento(@PathVariable String documento) {
		return empleadoService.empleadoByDocumento(documento);
	}
	
	@GetMapping("/provincia/{provincia}")
	@ResponseStatus(HttpStatus.OK)
	public List<Empleado> empleadosByProvincia(@PathVariable String provincia){
		List<Empleado> empleados = empleadoService.empleadosByProvincia(provincia);
		return empleados;
	}
	@GetMapping("/apellido/{apellido}")
	@ResponseStatus(HttpStatus.OK)
	public List<Empleado> empleadosByApellido(@PathVariable String apellido){
		List<Empleado> empleados = empleadoService.empleadosByApellido(apellido);
		return empleados;
	}
}
