package ir.parto.crm.modules.payment.model.repository;

import ir.parto.crm.modules.payment.model.entity.PaymentVendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentVendorRepository extends JpaRepository<PaymentVendor, Long>, RepositoryInterface {
    List<PaymentVendor> findAllByIsDeletedIsNull();

    Page<PaymentVendor> findAllByIsDeletedIsNull(Pageable pageable);

    PaymentVendor findByIsDeletedIsNullAndVendorId(Long id);

    boolean existsByIsDeletedIsNullAndVendorId(Long id);
}
