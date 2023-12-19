package br.com.portifolify.application.dataprovider.impl.event.record;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDomainEventRecord {

    @NonNull
    private String uuid;

    @NonNull
    private String document;

    @NonNull
    private String firstName;

    @NonNull
    private String status;

    @NonNull
    private String lastName;

    @NonNull
    private Integer scorePercentage;

    @NonNull
    private LocalDateTime scoreProcessingDate;

}
