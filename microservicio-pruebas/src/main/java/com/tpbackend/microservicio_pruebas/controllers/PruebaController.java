package com.tpbackend.microservicio_pruebas.controllers;


import com.tpbackend.microservicio_pruebas.dtos.*;
import com.tpbackend.microservicio_pruebas.entities.NotificacionTelefono;
import com.tpbackend.microservicio_pruebas.entities.Prueba;
import com.tpbackend.microservicio_pruebas.services.NotificacionTelefonoServiceImpl;
import com.tpbackend.microservicio_pruebas.services.PruebaServiceImpl;
import com.tpbackend.microservicio_pruebas.services.ReporteServiceImpl;
import com.tpbackend.microservicio_pruebas.services.VehiculoServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    @Autowired
    private PruebaServiceImpl pruebaService;

    @Autowired
    private VehiculoServiceImpl vehiculoService;

    @Autowired
    private NotificacionTelefonoServiceImpl notificacionTelefonoService;

    @Autowired
    private ReporteServiceImpl reporteService;

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Microservicio pruebas en ejecución...");
    }

    // Endpoint de generar la prueba punto 1.a
    /*
        a. Crear una nueva prueba, validando que el cliente no tenga la licencia vencida ni que
        esté restringido para probar vehículos en la agencia.
        Vamos a asumir que un interesado puede tener una única licencia registrada en el sistema
        y que todos los vehículos están patentados. También deben realizarse los controles razonables
        del caso; por ejemplo, que un mismo vehículo no esté siendo probado en ese mismo momento.
    */
    @PostMapping()
    public ResponseEntity<String> guardarPrueba(@RequestBody Prueba prueba){
        String error = pruebaService.validarPreparacionPrueba(prueba);
        if (error == null ){
            pruebaService.guardarPrueba(prueba);
            return ResponseEntity.ok("La Prueba fue creada exitosamente");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Endpoint de Listar las pruebas en curso 1.b
    /*
        b. Listar todas las pruebas en curso en un momento dado
    */
    @GetMapping("/en_curso")
    public List<Prueba> obtenerPruebasEnCurso(){
        LocalDateTime fechaActual = LocalDateTime.now();
        return pruebaService.buscarPruebasEnCurso(fechaActual);
    }

    // Endpoint de Finalizar una prueba, permitiendo al empleado agregar un comentario 1.c
    /*
        c. Finalizar una prueba, permitiéndole al empleado agregar un comentario sobre la misma.
    */
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<String> terminarPrueba(@PathVariable("id") Long id, @RequestBody String comentario){
        Prueba prueba = pruebaService.findById(id);
        if (prueba == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La prueba no fue encontrada.");
        }

        LocalDateTime fechaActual = LocalDateTime.now();
        if(fechaActual.isBefore(prueba.getFechaHoraInicio()) || fechaActual.isAfter(prueba.getFechaHoraFin())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La prueba no está en curso.");
        }

        prueba.setFechaHoraFin(fechaActual);
        prueba.setActiva(false);
        prueba.setComentarios(comentario);
        pruebaService.guardarPrueba(prueba);

        return ResponseEntity.ok("La prueba fue finalizada exitosamente.");
    }

    // Endpoint que soluciona el punto E, pero utiliza las condiciones del punto D;
    /*
        e. Enviar una notificación de promoción a uno o más teléfonos.
        Aplican las mismas consideraciones que en el punto anterior.
    */
    @PostMapping("/notificar")
    public ResponseEntity<String> guardarPrueba(@RequestBody NotificacionTelefono notificacionTelefono) {
        notificacionTelefonoService.guardarNotificacion(notificacionTelefono);
        return ResponseEntity.ok("Los usuarios fueron notificados.");
    }

    // Endpoints de los Reportes

    // Punto f

    // i. Incidentes (pruebas donde se excedieron los límites establecidos)
    @GetMapping("/reporte/incidentes")
    public void generarReporteIncidentes(HttpServletResponse res) throws IOException {
        //Seteamos el tipo de contenido en este caso un excel, permitiendole saber al navegador que va a dar un archivo excel
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        // acá indica que se va a descargar el archivo sugiriendo el nombre de Reporte_Incidente
        res.setHeader("Content-Disposition", "attachment; filename=Reporte_Incidentes.xlsx");

        // Buscamos los incidentes llenando la lista
        List<IncidentesDTO> incidentes = pruebaService.buscarIncidentes();

        // Se genera el reporte y se lo escribe como respuesta http.
        byte[] excel = reporteService.generarReporteIncidentes(incidentes);
        res.getOutputStream().write(excel);
    }

    // ii. Detalle de incidentes para un empleado.
    @GetMapping("/reporte/incidentes/{id}")
    public void generarReporteIncidenteDeEmpleado(@PathVariable("id") Long legajo, HttpServletResponse res) throws IOException {
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; filename=Reporte_Incidentes_Empleado_" + legajo + ".xlsx");

        List<IncidenteDeEmpleadoDTO> incidenteDeEmpleado = pruebaService.buscarIncidentesParaUnEmpleado(legajo);

        byte[] excel = reporteService.generarReporteIncidenteDeEmpleado(incidenteDeEmpleado);
        res.getOutputStream().write(excel);

    }

    // iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un período determinado
    @GetMapping("/reporte/cant_km_vehiculo/{id}")
    public void generarReporteCantKmRecorridosPorVehiculo(@PathVariable("id") Long idVehiculo,
                                                          @RequestParam("fechaHoraInicio") String fechaHoraInicio,
                                                          @RequestParam("fechaHoraFin") String fechaHoraFin,
                                                          HttpServletResponse res) throws IOException{
        LocalDateTime fechaHoraInicial = LocalDateTime.parse(fechaHoraInicio);
        LocalDateTime fechaHoraFinal = LocalDateTime.parse(fechaHoraFin);

        List<CantKmRegistradosPorVehiculoDTO> cantKmRegistradosPorVehiculo = vehiculoService.buscarKmRegistradosPorVehiculos(fechaHoraInicial, fechaHoraFinal, idVehiculo);
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; filename=Reporte_Cant_Km_Por_Vehiculo_" + idVehiculo + ".xlsx");

        byte[] excel = reporteService.generarReporteCantKmRegistradosPorVehiculo(cantKmRegistradosPorVehiculo);
        res.getOutputStream().write(excel);
    }

    // iv. Detalle de pruebas realizadas para un vehículo
    @GetMapping("/reporte/detalle_vehiculo/{id}")
    public void generarReporteDetallesPruebaVehiculo(@PathVariable("id") Long idVehiculo, HttpServletResponse res) throws IOException{
        List<DetallesPruebaVehiculoDTO> pruebasPorVehiculo = vehiculoService.buscarPruebaPorIdVehiculo(idVehiculo);

        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; filename=Reporte_Pruebas_Por_Vehiculo_" + idVehiculo + ".xlsx");

        byte[] excel = reporteService.generarReporteDetallesPruebaVehiculo(pruebasPorVehiculo);
        res.getOutputStream().write(excel);
    }
}
