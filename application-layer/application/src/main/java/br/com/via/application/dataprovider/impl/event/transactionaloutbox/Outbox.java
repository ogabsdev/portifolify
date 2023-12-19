package br.com.via.application.dataprovider.impl.event.transactionaloutbox;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public abstract class Outbox {

    protected Outbox(String topicName) {
        setTopicName(topicName);
        setStatus(OutboxStatus.PENDING);
    }

    public abstract String getTableName();

    public abstract String getTopicName();

    public abstract void setTopicName(String topicName);

    public abstract Long getId();

    public abstract LocalDateTime getCreationDate();

    public abstract void setCreationDate(LocalDateTime creationDate);

    public abstract LocalDateTime getProcessedDate();

    public abstract void setProcessedDate(LocalDateTime processedDate);

    public abstract String getEventData();

    public abstract void setEventData(String eventData);

    public abstract OutboxStatus getStatus();

    protected abstract void setStatus(OutboxStatus status);

    public abstract Long getOffset();

    protected abstract void setOffset(Long status);

    public abstract Integer getPartition();

    protected abstract void setPartition(Integer status);

    public void markAsProcessed(Long offset, Integer partition) {
        setOffset(offset);
        setPartition(partition);
        setProcessedDate(LocalDateTime.now());
        setStatus(OutboxStatus.PROCESSED);
    }

}
