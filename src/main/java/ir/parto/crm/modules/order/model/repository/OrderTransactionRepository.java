package ir.parto.crm.modules.order.model.repository;

import ir.parto.crm.modules.order.model.entity.OrderTransaction;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTransactionRepository extends JpaRepository<OrderTransaction, Long>, RepositoryInterface<OrderTransaction> {
    List<OrderTransaction> findAllByIsDeletedIsNull();

    OrderTransaction findByIsDeletedIsNullAndOrderTransactionId(Long id);
}
