package br.com.portifolify.domain;

import br.com.portifolify.domain.vo.Cpf;
import br.com.portifolify.domain.vo.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Manager implements Employee {

    @NonNull
    private Id id;

    @NonNull
    private String name;

    @NonNull
    private LocalDate dateOfBirth;

    @NonNull
    private Cpf cpf;

}
