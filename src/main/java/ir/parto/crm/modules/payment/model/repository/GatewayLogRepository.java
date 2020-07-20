package ir.parto.crm.modules.payment.model.repository;

import ir.parto.crm.modules.payment.model.entity.GatewayLog;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayLogRepository extends JpaRepository<GatewayLog, Long>, RepositoryInterface<GatewayLog> {
    List<GatewayLog> findAllByIsDeletedIsNull();

    Page<GatewayLog> findAllByIsDeletedIsNull(Pageable pageable);

    GatewayLog findByIsDeletedIsNullAndGatewayLogId(Long id);

    boolean existsByIsDeletedIsNullAndGatewayLogId(Long id);
}
