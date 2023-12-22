package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.MemberDTO;

import java.util.List;

public interface FindAllMemberUseCase {

    List<MemberDTO> find();

}
