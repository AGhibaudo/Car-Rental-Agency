package com.tpbackend.microservicio_pruebas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Pruebas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FECHA_HORA_INICIO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String fechaHoraInicio;

    @Column(name = "FEHA_HORA_FIN")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentarios;

    @Column(name = "CANTIDAD_KM_REGISTRADOS")
    private String cantKmRegistrados;

    // Relaciones con las demás tablas
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO", nullable = false)
    private Interesado interesado;

    // Override Getter
    public String getCantKmRegistrados(){
        return cantKmRegistrados + " Km";
    }
}
