package br.com.via.application.dataprovider.impl.event.record.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainEventEnvelope<T> {

    @NonNull
    public String eventReason;

    @NonNull
    public Long contentVersion;

    @NonNull
    public T content;

}
