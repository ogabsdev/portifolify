package br.com.portifolify.domain;

import br.com.portifolify.domain.vo.Cpf;
import br.com.portifolify.domain.vo.Id;

import java.time.LocalDate;

public interface Employee {

    Id getId();

    String getName();

    LocalDate getDateOfBirth();

    Cpf getCpf();

}
