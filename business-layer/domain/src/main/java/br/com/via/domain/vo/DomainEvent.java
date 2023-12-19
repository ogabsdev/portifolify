package br.com.via.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainEvent<T> {

    @NonNull
    private final T content;

    @NonNull
    private final String eventReason;

}
