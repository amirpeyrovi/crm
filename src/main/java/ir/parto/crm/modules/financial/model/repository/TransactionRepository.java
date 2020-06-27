package ir.parto.crm.modules.financial.model.repository;

import ir.parto.crm.modules.financial.model.entity.Transaction;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, RepositoryInterface<Transaction> {
    List<Transaction> findAllByIsDeletedIsNull();

    Transaction findByIsDeletedIsNullAndTransactionId(Long id);
}
