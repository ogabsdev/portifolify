package br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.common.VersionableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionalOutboxInstruction {

    @NonNull
    private Object eventObject;

    @NonNull
    private String eventReason;

    @NonNull
    private VersionableEntity eventAssociatedEntity;

    @NonNull
    private Outbox outbox;

}
