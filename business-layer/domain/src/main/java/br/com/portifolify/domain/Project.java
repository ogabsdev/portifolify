package br.com.portifolify.domain;

import br.com.portifolify.domain.vo.Id;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Project {

    @NonNull
    private Id id;

    @NonNull
    private String name;

    @NonNull
    private ZonedDateTime startDate;

    private ZonedDateTime expectedEndDate;

    private ZonedDateTime realEndDate;

    @NonNull
    private ProjectRisk projectRisk;

    private String description;

    private Double budget;

    @NonNull
    private Manager manager;

    @NonNull
    private ProjectStatus projectStatus;

    public static ProjectBuilder builder() {
        return new CustomProjectBuilder();
    }

    private static class CustomProjectBuilder extends ProjectBuilder {

        @Override
        public Project build() {
            validate();
            return super.build();
        }

        private void validate() {
        }
    }
}
