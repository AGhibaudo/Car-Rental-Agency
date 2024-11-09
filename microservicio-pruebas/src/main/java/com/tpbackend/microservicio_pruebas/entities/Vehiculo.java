package com.tpbackend.microservicio_pruebas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Vehiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PATENTE")
    private String patente;

    @Column(name = "ANIO")
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "ID_MODELO", nullable = false)
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "ID_POSICION", nullable = false)
    private Posicion posicion;
}
