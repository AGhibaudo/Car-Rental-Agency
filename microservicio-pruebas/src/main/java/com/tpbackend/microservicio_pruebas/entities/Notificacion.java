package com.tpbackend.microservicio_pruebas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "LEGAJO")
    private Long legajo;

    @Column(name = "TELEFONO_CONTACTO_INTERESADO")
    private String telefonoInteresado;
}