package ir.parto.crm.modules.reseller.model.repository;

import ir.parto.crm.modules.reseller.model.entity.ResellerCommission;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResellerCommissionRepository extends JpaRepository<ResellerCommission, Long>, RepositoryInterface<ResellerCommission> {
    List<ResellerCommission> findAllByIsDeletedIsNull();

    Page<ResellerCommission> findAllByIsDeletedIsNull(Pageable pageable);

    ResellerCommission findByIsDeletedIsNullAndResellerCommissionId(Long id);

    boolean existsByIsDeletedIsNullAndResellerCommissionId(Long id);
}
