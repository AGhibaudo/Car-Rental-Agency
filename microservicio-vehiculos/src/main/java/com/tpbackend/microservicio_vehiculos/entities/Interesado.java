package com.tpbackend.microservicio_vehiculos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "Interesados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Interesado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "RESTRINGIDO")
    private Boolean restringido;

    @Column(name = "NRO_LICENCIA", unique = true)
    private Long numeroLicencia;

    @Column(name = "TELEFONO_CONTACTO")
    private Long telefonoContacto;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVencimientoLicencia;

}
