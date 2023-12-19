package br.com.via.application.dataprovider.impl.persistence.entity;

import br.com.via.application.dataprovider.impl.persistence.entity.common.VersionableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements VersionableEntity {

    @Id
    @Column(name = "COD_UUID", nullable = false, unique = true, updatable = false)
    private String uuid;

    @Column(name = "DOCUMENT", nullable = false, unique = true)
    private String document;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "SCORE", nullable = false)
    private Integer scorePercentage;

    @Column(name = "SCORE_PROCESSING_DATE", nullable = false)
    private LocalDateTime scoreProcessingDate;

    @Version
    private Long version;

}
