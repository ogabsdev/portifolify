package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.core.dataprovider.persistence.dao.MemberDAO;
import br.com.portifolify.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

    @Override
    public List<Member> findAll() {
        return null;
    }

}
