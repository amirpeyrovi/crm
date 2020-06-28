package ir.parto.crm.modules.order.model.repository;

import ir.parto.crm.modules.order.model.entity.Order;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, RepositoryInterface<Order> {
    List<Order> findAllByIsDeletedIsNull();

    Order findByIsDeletedIsNullAndOrderId(Long id);
}
