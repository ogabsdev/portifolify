package br.com.portifolify.application.dataprovider.impl.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "projeto")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String name;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate startDate;

    @Column(name = "data_previsao_fim", nullable = false)
    private LocalDate expectedEndDate;

    @Column(name = "data_fim")
    private LocalDate realEndDate;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(name = "orcamento", nullable = false)
    private Double budget;

    @Column(name = "risco", nullable = false)
    private String projectRisk;

    @Column(name = "status", nullable = false)
    private String projectStatus;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "idgerente", referencedColumnName = "id")
    private PeopleEntity manager;

}
