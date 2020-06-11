package con.aquispe.backend.service;

import java.util.List;
import java.util.Optional;

import con.aquispe.backend.models.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado>findAll();
	
	public Optional<Empleado> findById(Long id);
	
	public void deleteById(Long id);
	
	public Empleado save(Empleado empleado);
	
	public Empleado empleadoByDocumento(String documento);
	
	public List<Empleado> empleadosByProvincia(String provincia);
	
	public List<Empleado> empleadosByApellido(String apellido);
}
