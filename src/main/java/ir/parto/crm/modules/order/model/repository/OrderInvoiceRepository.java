package ir.parto.crm.modules.order.model.repository;

import ir.parto.crm.modules.order.model.entity.OrderInvoice;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInvoiceRepository extends JpaRepository<OrderInvoice,Long>, RepositoryInterface<OrderInvoice> {
}
