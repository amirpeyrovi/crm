package ir.parto.crm.modules.order.model.repository;

import ir.parto.crm.modules.order.model.entity.OrderItem;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, RepositoryInterface<OrderItem> {
    List<OrderItem> findAllByIsDeletedIsNull();

    OrderItem findByIsDeletedIsNullAndOrderItemId(Long id);
}
