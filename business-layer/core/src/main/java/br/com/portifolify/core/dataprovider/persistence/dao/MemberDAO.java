package br.com.portifolify.core.dataprovider.persistence.dao;

import br.com.portifolify.domain.Member;

import java.util.List;

public interface MemberDAO {

    List<Member> findAll();

}
