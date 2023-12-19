package br.com.via.application.dataprovider.impl.persistence.entity;

import br.com.via.application.dataprovider.impl.event.transactionaloutbox.Outbox;
import br.com.via.application.dataprovider.impl.event.transactionaloutbox.OutboxStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.com.via.application.dataprovider.impl.persistence.entity.CustomerOutboxEntity.TABLE_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = TABLE_NAME)
public class CustomerOutboxEntity extends Outbox {

    static final String TABLE_NAME = "CUSTOMER_OUTBOX";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OutboxStatus status;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "PROCESSED_DATE")
    private LocalDateTime processedDate;

    @Lob
    @Column(name = "EVENT_DATA", nullable = false)
    private String eventData;

    @Column(name = "TOPIC_NAME", nullable = false)
    private String topicName;

    @Column(name = "OFFSET_NUMBER")
    private Long offset;

    @Column(name = "PARTITION_NUMBER")
    private Integer partition;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public CustomerOutboxEntity(String topicName) {
        super(topicName);
    }

}
