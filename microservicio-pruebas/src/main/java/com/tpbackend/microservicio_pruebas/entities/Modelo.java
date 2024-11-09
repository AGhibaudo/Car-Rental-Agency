package com.tpbackend.microservicio_pruebas.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Modelos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA", nullable = false)
    private Marca marca;
}
