package com.tpbackend.microservicio_pruebas.entities;

import lombok.Data;
import java.util.List;

@Data
public class NotificacionTelefono {
    private Notificacion notificacion;
    private List<Long> telefonos;

}
