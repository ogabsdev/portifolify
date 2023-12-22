package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.MemberDAO;
import br.com.portifolify.core.service.FindAllMemberService;
import br.com.portifolify.domain.Member;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
class FindAllMemberServiceImpl implements FindAllMemberService {

    private final MemberDAO memberDAO;

    @Override
    public List<Member> findAll() {
        return memberDAO.findAll();
    }

}
