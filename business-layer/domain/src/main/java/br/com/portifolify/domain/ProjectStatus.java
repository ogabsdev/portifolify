package br.com.portifolify.domain;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectStatus {

    @NonNull
    private String description;

    private boolean canDelete;

}
