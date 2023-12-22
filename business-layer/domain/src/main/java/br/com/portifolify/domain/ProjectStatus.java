package br.com.portifolify.domain;

import br.com.portifolify.domain.vo.Id;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectStatus {

    @NonNull
    private Id id;

    @NonNull
    private String description;

    private boolean canDelete;

}
