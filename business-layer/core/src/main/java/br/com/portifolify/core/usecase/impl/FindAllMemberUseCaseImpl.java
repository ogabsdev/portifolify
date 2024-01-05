package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.FindAllMemberService;
import br.com.portifolify.core.usecase.FindAllMemberUseCase;
import br.com.portifolify.core.usecase.dto.MemberDTO;
import br.com.portifolify.core.usecase.dto.converter.MemberUseCaseConverter;
import br.com.portifolify.domain.Member;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Transactional
@RequiredArgsConstructor
class FindAllMemberUseCaseImpl implements FindAllMemberUseCase {

    private final FindAllMemberService findAllMemberService;

    private final MemberUseCaseConverter memberUseCaseConverter;

    @Override
    public List<MemberDTO> find() {

        List<Member> members = findAllMemberService.findAll();

        return memberUseCaseConverter.convert(members);
    }

}
