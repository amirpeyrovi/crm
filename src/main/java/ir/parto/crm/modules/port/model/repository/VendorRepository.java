package ir.parto.crm.modules.port.model.repository;

import ir.parto.crm.modules.port.model.entity.Vendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>, RepositoryInterface<Vendor> {
    List<Vendor> findAllByIsDeletedIsNull();

    Page<Vendor> findAllByIsDeletedIsNull(Pageable pageable);

    Vendor findByIsDeletedIsNullAndVendorId(Long id);

    boolean existsByIsDeletedIsNullAndVendorId(Long id);
}
