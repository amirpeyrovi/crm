package ir.parto.crm.modules.financial.model.repository;

import ir.parto.crm.modules.financial.model.entity.Invoice;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, RepositoryInterface<Invoice> {
    List<Invoice> findAllByIsDeletedIsNull();

    Invoice findByIsDeletedIsNullAndInvoiceId(Long id);
}
