package com.tpbackend.microservicio_pruebas.services;

import com.tpbackend.microservicio_pruebas.dtos.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ReporteServiceImpl {
    // Generación de reportes.
    // i. Incidentes (pruebas donde se excedieron los límites establecidos).
    public byte[] generarReporteIncidentes(List<IncidentesDTO> incidentes) throws IOException {
        Workbook excel = new XSSFWorkbook();
        Sheet hoja = excel.createSheet("Incidentes");
        Row cabezera = hoja.createRow(0);
        // Indicamos el encabezado que tendrá la tabla dentro del Excel
        cabezera.createCell(0).setCellValue("ID - Incidente");
        cabezera.createCell(1).setCellValue("Fecha Hora Inicio");
        cabezera.createCell(2).setCellValue("Fecha Hora Fin");
        cabezera.createCell(3).setCellValue("Legajo del Empleado");
        cabezera.createCell(4).setCellValue("Nombre del Empleado");
        cabezera.createCell(5).setCellValue("Apellido del Empleado");
        cabezera.createCell(6).setCellValue("Nombre del Interesado");
        cabezera.createCell(7).setCellValue("Descripción");
        cabezera.createCell(8).setCellValue("Patente Vehículo");
        // Guardamos los datos en base a la columna de la cabezera.
        int nroFila = 1;
        for (IncidentesDTO incidente : incidentes) {
            Row fila = hoja.createRow(nroFila++);
            /*
            Por cada iteración traeremos los datos que obtuvimos de la Query Incidentes, para ello debemos
            crear una fila y una vez ingresado todos los datos, sumarle un numero más para crear otra fila.
            */
            fila.createCell(0).setCellValue(incidente.getId());
            fila.createCell(1).setCellValue(incidente.getFechaHoraInicio().toString());
            fila.createCell(2).setCellValue(incidente.getFechaHoraFin().toString());
            fila.createCell(3).setCellValue(incidente.getLegajoEmpleado());
            fila.createCell(4).setCellValue(incidente.getNombreEmpleado());
            fila.createCell(5).setCellValue(incidente.getApellidoEmpleado());
            fila.createCell(6).setCellValue(incidente.getNombreInteresado());
            fila.createCell(7).setCellValue(incidente.getApellidoInteresado());
            fila.createCell(8).setCellValue(incidente.getDescripcionNotificacion());
            fila.createCell(9).setCellValue(incidente.getPatente());
            // Lo que hacemos acá es mediante el DTO traer los datos pertenecientes al reporte que se va a crear.
        }
        try (ByteArrayOutputStream salida = new ByteArrayOutputStream()){
            excel.write(salida);
            return salida.toByteArray();
        } finally {
            excel.close();
        }
    }

    // ii. Detalle de incidentes para un empleado.
    public byte[] generarReporteIncidenteDeEmpleado(List<IncidenteDeEmpleadoDTO> incidentesEmpleado) throws IOException {
        Workbook excel = new XSSFWorkbook();
        Sheet hoja = excel.createSheet("IncidentesParaUnEmpleado");
        Row cabezera = hoja.createRow(0);
        // Indicamos el encabezado que tendrá la tabla dentro del Excel
        cabezera.createCell(0).setCellValue("Legajo del Empleado");
        cabezera.createCell(1).setCellValue("Apellido");
        cabezera.createCell(2).setCellValue("Nombre");
        cabezera.createCell(3).setCellValue("Télefono");
        cabezera.createCell(4).setCellValue("Patente Vehículo");
        cabezera.createCell(5).setCellValue("Incidente");
        int nroFila = 1;
        for (IncidenteDeEmpleadoDTO incidente : incidentesEmpleado) {
            Row fila = hoja.createRow(nroFila++);
            /*
            Por cada iteración traeremos los datos que obtuvimos de la Query Incidentes, para ello debemos
            crear una fila y una vez ingresado todos los datos, sumarle un numero más para crear otra fila.
            */
            fila.createCell(0).setCellValue(incidente.getLegajo());
            fila.createCell(1).setCellValue(incidente.getEmpleadoApellido());
            fila.createCell(2).setCellValue(incidente.getEmpleadoNombre());
            fila.createCell(3).setCellValue(incidente.getTelefonoContacto());
            fila.createCell(4).setCellValue(incidente.getPatente());
            fila.createCell(5).setCellValue(incidente.getIncidente());
        }
        try (ByteArrayOutputStream salida = new ByteArrayOutputStream()){
            excel.write(salida);
            return salida.toByteArray();
        } finally {
            excel.close();
        }
    }

    // iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un período determinado.
    public byte[] generarReporteCantKmRegistradosPorVehiculo(List<CantKmRegistradosPorVehiculoDTO> cantKmRegistrados) throws IOException {
        Workbook excel = new XSSFWorkbook();
        Sheet hoja = excel.createSheet("Cantidad de Kilometros Registrados");
        Row cabezera = hoja.createRow(0);

        // Indicamos el encabezado que tendrá la tabla dentro del Excel
        cabezera.createCell(0).setCellValue("ID - Vehiculo");
        cabezera.createCell(1).setCellValue("Fecha Hora Inicio");
        cabezera.createCell(2).setCellValue("Fecha Hora Fin");
        cabezera.createCell(3).setCellValue("Patente");
        cabezera.createCell(4).setCellValue("Kilometros Registrados");

        int nroFila = 1;
        for (CantKmRegistradosPorVehiculoDTO cantKmRegistradosPorVehiculo : cantKmRegistrados) {
            Row fila = hoja.createRow(nroFila++);

            fila.createCell(0).setCellValue(cantKmRegistradosPorVehiculo.getId());
            fila.createCell(1).setCellValue(cantKmRegistradosPorVehiculo.getFechaHoraInicio().toString());
            fila.createCell(2).setCellValue(cantKmRegistradosPorVehiculo.getFechaHoraFin().toString());
            fila.createCell(3).setCellValue(cantKmRegistradosPorVehiculo.getPatente());
            fila.createCell(4).setCellValue(cantKmRegistradosPorVehiculo.getKmRegistrados());
        }
        try (ByteArrayOutputStream salida = new ByteArrayOutputStream()){
            excel.write(salida);
            return salida.toByteArray();
        } finally {
            excel.close();
        }
    }

    // iv. Detalles de pruebas realizadas para un vehículo.
    public byte[] generarReporteDetallesPruebaVehiculo(List<DetallesPruebaVehiculoDTO> pruebasVehiculos) throws IOException{
        Workbook excel = new XSSFWorkbook();
        Sheet hoja = excel.createSheet("Detalles de Prueba Por Vehiculo");
        Row cabezera = hoja.createRow(0);

        // Indicamos el encabezado que tendrá la tabla dentro del Excel
        cabezera.createCell(0).setCellValue("ID - Vehiculo");
        cabezera.createCell(1).setCellValue("Patente");
        cabezera.createCell(2).setCellValue("Modelo");
        cabezera.createCell(3).setCellValue("Marca");
        cabezera.createCell(4).setCellValue("Fecha Hora Inicio");
        cabezera.createCell(5).setCellValue("Fecha Hora Fin");
        cabezera.createCell(6).setCellValue("Tipo Documento del Interesado");
        cabezera.createCell(7).setCellValue("Documento del Interesado");
        cabezera.createCell(8).setCellValue("Apellido del Interesado");
        cabezera.createCell(9).setCellValue("Nombre del Interesado");
        cabezera.createCell(10).setCellValue("Número de Licencia");
        cabezera.createCell(11).setCellValue("Fecha de Vencimiendo de la Licencia");
        cabezera.createCell(12).setCellValue("Lejago del Empleado");
        cabezera.createCell(13).setCellValue("Apellido del Empleado");
        cabezera.createCell(14).setCellValue("Nombre del Empleado");
        cabezera.createCell(15).setCellValue("Comentarios");

        int nroFila = 1;
        for (DetallesPruebaVehiculoDTO detallePrueba : pruebasVehiculos) {
            Row fila = hoja.createRow(nroFila++);

            fila.createCell(0).setCellValue(detallePrueba.getVehiculoId());
            fila.createCell(1).setCellValue(detallePrueba.getPatente());
            fila.createCell(2).setCellValue(detallePrueba.getDescripcionModelo());
            fila.createCell(3).setCellValue(detallePrueba.getNombreMarca());
            fila.createCell(4).setCellValue(detallePrueba.getFechaHoraInicio().toString());
            fila.createCell(5).setCellValue(detallePrueba.getFechaHoraFin().toString());
            fila.createCell(6).setCellValue(detallePrueba.getTipoDocumentoInteresado());
            fila.createCell(7).setCellValue(detallePrueba.getDocumentoInteresado());
            fila.createCell(8).setCellValue(detallePrueba.getApellidoInteresado());
            fila.createCell(9).setCellValue(detallePrueba.getNombreInteresado());
            fila.createCell(10).setCellValue(detallePrueba.getNroLicenciaInteresado());
            fila.createCell(11).setCellValue(detallePrueba.getFechaVencimientoLicencia().toString());
            fila.createCell(12).setCellValue(detallePrueba.getLegajoEmpleado());
            fila.createCell(13).setCellValue(detallePrueba.getApellidoEmpleado());
            fila.createCell(14).setCellValue(detallePrueba.getNombreEmpleado());
            fila.createCell(15).setCellValue(detallePrueba.getComentarios());
        }
        try (ByteArrayOutputStream salida = new ByteArrayOutputStream()){
            excel.write(salida);
            return salida.toByteArray();
        } finally {
            excel.close();
        }
    }
}