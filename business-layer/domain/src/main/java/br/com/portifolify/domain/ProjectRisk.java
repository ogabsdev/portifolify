package br.com.portifolify.domain;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectRisk {

    @NonNull
    private String description;

}
