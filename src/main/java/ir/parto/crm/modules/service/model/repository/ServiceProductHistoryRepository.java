package ir.parto.crm.modules.service.model.repository;

import ir.parto.crm.modules.service.model.entity.ServiceProductHistory;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProductHistoryRepository extends JpaRepository<ServiceProductHistory, Long>, RepositoryInterface<ServiceProductHistory> {
}
