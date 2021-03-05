package org.wuphf.assessment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wuphf.assessment.model.jpa.FreeWagersJpa;
import org.wuphf.assessment.model.jpa.TransactionJpa;

import java.util.List;

@Repository
public interface FreeWagersRepository extends PagingAndSortingRepository<FreeWagersJpa, Long> {

    public List<FreeWagersJpa> findAllByPlayerIdOrderByUidDesc(long playerId);

}
