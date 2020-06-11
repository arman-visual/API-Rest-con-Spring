package con.aquispe.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import con.aquispe.backend.models.entity.Empleado;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Long>{

}
