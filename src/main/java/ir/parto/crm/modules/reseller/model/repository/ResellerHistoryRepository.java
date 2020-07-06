package ir.parto.crm.modules.reseller.model.repository;

import ir.parto.crm.modules.reseller.model.entity.ResellerHistory;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResellerHistoryRepository extends JpaRepository<ResellerHistory, Long>, RepositoryInterface<ResellerHistory> {
    List<ResellerHistory> findAllByIsDeletedIsNull();

    Page<ResellerHistory> findAllByIsDeletedIsNull(Pageable pageable);

    ResellerHistory findByIsDeletedIsNullAndResellerHistoryId(Long id);

    boolean existsByIsDeletedIsNullAndResellerHistoryId(Long id);
}
