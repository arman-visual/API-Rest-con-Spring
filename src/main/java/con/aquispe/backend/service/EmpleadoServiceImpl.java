package con.aquispe.backend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import con.aquispe.backend.models.dao.IEmpleadoDAO;
import con.aquispe.backend.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoServiceImpl.class);
	
	@Autowired
	private EntityManager em;
	@Autowired
	private IEmpleadoDAO empleadoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Empleado> findById(Long id) {
		return empleadoDAO.findById(id);
		//revisar este metodo
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		empleadoDAO.deleteById(id);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoDAO.save(empleado);
	}

	@Override
	public Empleado empleadoByDocumento(String documento) {
		Empleado result = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empleado> cr = cb.createQuery(Empleado.class);
		Root<Empleado> root = cr.from(Empleado.class);
		cr.select(root).where(cb.equal(root.get("documento"), documento));
		try {
			result = em.createQuery(cr).getSingleResult();
		} catch (NoResultException nre) {
			LOGGER.error("No existe empleado con documento: " + documento);
		}catch (NonUniqueResultException nure) {
			LOGGER.error("Existe más de un empleado asociado al documento: " + documento);
		}
		
		return result;
	}

	@Override
	public List<Empleado> empleadosByProvincia(String provincia) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empleado> cr = cb.createQuery(Empleado.class);
		Root<Empleado> root = cr.from(Empleado.class);
		cr.select(root).where(cb.equal(root.get("provincia"), provincia));
		List<Empleado> empleados = em.createQuery(cr).getResultList();
		if(empleados.isEmpty()) {
			LOGGER.error("No se han encontrado empleados asociados con la provincia ".concat(provincia));
			throw new NoResultException("No existen empleados asociados con la provincia '".concat(provincia).concat("'"));
		}else {
			LOGGER.info("Se han encontrado empleados asociados a la provincia de '".concat(provincia).concat("'"));
			return empleados;
		}
		
	}

	@Override
	public List<Empleado> empleadosByApellido(String apellido) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empleado> cr = cb.createQuery(Empleado.class);
		Root<Empleado> root = cr.from(Empleado.class);
		cr.select(root).where(cb.like(root.get("apellidos"), "%" + apellido + "%"));
		List<Empleado> empleados = em.createQuery(cr).getResultList();
		if(empleados.equals(null) || empleados.isEmpty()) {
			LOGGER.error("No se han encontrado empleados con el apellido ".concat(apellido));
			throw new NoResultException("No se han encontrado empleados con el apellido '".concat(apellido).concat("'"));
		}else {
			LOGGER.info("Se han encontrado empleados con el apellido '".concat(apellido).concat("'"));
			return empleados;
		}
	}


}
