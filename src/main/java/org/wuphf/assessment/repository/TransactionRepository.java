package org.wuphf.assessment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wuphf.assessment.model.jpa.TransactionJpa;

import java.util.List;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<TransactionJpa, Long> {

    public List<TransactionJpa> findAllByPlayerId(long playerId);
    public List<TransactionJpa> findAllByPlayerIdAndFreeWagerUid(long playerId, long freeWagerUid);

    public TransactionJpa findByTransactionId(long transactionId);

    public List<TransactionJpa> findAllByPlayerIdOrderByUidDesc(long playerId);


}
