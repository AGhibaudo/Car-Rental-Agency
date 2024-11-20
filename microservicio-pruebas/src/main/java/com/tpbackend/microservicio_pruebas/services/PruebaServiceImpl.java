package com.tpbackend.microservicio_pruebas.services;

import com.tpbackend.microservicio_pruebas.entities.Empleado;
import com.tpbackend.microservicio_pruebas.entities.Interesado;
import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.entities.Vehiculo;
import com.tpbackend.microservicio_pruebas.repositories.PruebaRepository;
import com.tpbackend.microservicio_pruebas.services.interfaces.PruebaService;
import com.tpbackend.microservicio_pruebas.dtos.IncidentesDTO;
import com.tpbackend.microservicio_pruebas.dtos.IncidenteDeEmpleadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaServiceImpl extends ServiceImpl<Prueba, Long> implements PruebaService {
    // Nos traemos nuestro repository, y a su vez los demás services para implementarlos aqui.
    @Autowired
    PruebaRepository pruebaRepository;

    @Autowired
    VehiculoServiceImpl vehiculoService;

    @Autowired
    InteresadoServiceImpl interesadoService;

    @Autowired
    EmpleadoServiceImpl empleadoService;

    @Autowired
    PosicionServiceImpl posicionService;

    // Acá empezamos a realizar la lógica que utilizaremos en los endpoints.
    @Override
    public List<Prueba> buscarPruebasEnCurso(LocalDateTime fechaActual) {
        return pruebaRepository.buscarPruebasEnCurso(fechaActual);
    }

    @Override
    public Prueba guardarPrueba(Prueba prueba) {
        return pruebaRepository.save(prueba);
    }

    @Override
    public boolean elVehiculoEstaDisponible(Vehiculo vehiculo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        List<Prueba> pruebasEnCurso = buscarVehiculoYFecha(vehiculo, fechaHoraInicio, fechaHoraFin);
        return pruebasEnCurso.isEmpty();
    }

    @Override
    public List<Prueba> buscarVehiculoYFecha(Vehiculo vehiculo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        return pruebaRepository.buscarVehiculoYFecha(vehiculo, fechaHoraInicio, fechaHoraFin);
    }

    @Override
    public String validarPreparacionPrueba(Prueba prueba) {
        // Validacion para crear la entidad prueba.
        Empleado emp = empleadoService.findByLegajo(prueba.getEmpleado().getLegajo());
        if (emp == null) {
            return "El empleado no fue encontrado o no existe!";
        }

        Interesado interesado = interesadoService.findById(prueba.getInteresado().getId());
        if (interesado == null) {
            return "El Interesado no fue encontrado o no existe!";
        }

        Vehiculo vehiculo = vehiculoService.findById(prueba.getVehiculo().getId());
        if (vehiculo == null){
            return "El Vehiculo no fue encontrado o no existe!";
        }
        // Creacion de una nueva entidad prueba.
        prueba.setEmpleado(emp);
        prueba.setInteresado(interesado);
        prueba.setVehiculo(vehiculo);
        prueba.setActiva(true);
        // Validaciones Extras
        if (interesado.getRestringido()){
            return "El cliente esta restringido para realizar pruebas";
        }

        LocalDateTime fechaVencimiento = interesado.getFechaVencimientoLicencia();
        if (fechaVencimiento != null && fechaVencimiento.isBefore(LocalDateTime.now())){
            return "La licencia del cliente está vencida";
        }

        LocalDateTime fechaHoraInicio = prueba.getFechaHoraInicio();
        LocalDateTime fechaHoraFin = prueba.getFechaHoraFin();

        if(!elVehiculoEstaDisponible(vehiculo, fechaHoraInicio, fechaHoraFin)){
            return "El vehiculo no está disponible en el plazo índicado";
        }

        if(!empleadoService.estaDisponible(emp, fechaHoraInicio, fechaHoraFin)){
            return "El empleado no está disponible en el plazo índicado";
        }

        LocalDateTime fechaActual = LocalDateTime.now();
        if(prueba.getFechaHoraFin().isBefore(fechaActual)){
            prueba.setActiva(false);
        }

        return null;
    }

    // Reportes 1° punto F ->  i & ii
    public List<IncidentesDTO> buscarIncidentes(){
        return pruebaRepository.buscarIncidentes();
    }

    public List<IncidenteDeEmpleadoDTO> buscarIncidentesParaUnEmpleado(Long legajo){
        return pruebaRepository.buscarIncidentesParaUnEmpleado(legajo);
    }

    @Override
    public Prueba create(Prueba entity) {
        pruebaRepository.save(entity);
        return entity;
    }

    @Override
    public Prueba update(Prueba entity) {
        pruebaRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Prueba entity = findById(id);
        pruebaRepository.delete(entity);
    }

    @Override
    public Prueba findById(Long id) {
        return pruebaRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Prueba> findAll() {
        return pruebaRepository.findAll();
    }

    @Override
    public boolean existById(Long id) {
        return pruebaRepository.existsById(id);
    }
}