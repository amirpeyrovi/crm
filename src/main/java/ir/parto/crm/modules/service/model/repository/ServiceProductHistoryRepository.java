package ir.parto.crm.modules.service.model.repository;

import ir.parto.crm.modules.service.model.entity.ServiceProductHistory;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProductHistoryRepository extends JpaRepository<ServiceProductHistory, Long>, RepositoryInterface<ServiceProductHistory> {
    ServiceProductHistory findByIsDeletedIsNullAndServiceProductHistoryId(Long serviceProductHistoryId);

    List<ServiceProductHistory> findAllByIsDeletedIsNull();

    Page<ServiceProductHistory> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndServiceProductHistoryId(Long id);
}
