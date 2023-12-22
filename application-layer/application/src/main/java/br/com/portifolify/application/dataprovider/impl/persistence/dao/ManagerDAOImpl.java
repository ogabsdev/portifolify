package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.core.dataprovider.persistence.dao.ManagerDAO;
import br.com.portifolify.domain.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagerDAOImpl implements ManagerDAO {

    @Override
    public List<Manager> findAll() {
        return null;
    }

}
