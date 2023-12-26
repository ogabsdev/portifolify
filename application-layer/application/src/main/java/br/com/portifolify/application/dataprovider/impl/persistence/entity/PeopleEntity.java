package br.com.portifolify.application.dataprovider.impl.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @Column(name = "datanascimento")
    private LocalDate dateOfBirth;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "funcionario")
    private boolean isEmployee;

    @Column(name = "gerente")
    private boolean isManager;

}
