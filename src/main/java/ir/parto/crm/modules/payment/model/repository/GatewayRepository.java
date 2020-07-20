package ir.parto.crm.modules.payment.model.repository;

import ir.parto.crm.modules.payment.model.entity.Gateway;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long>, RepositoryInterface<Gateway> {
    List<Gateway> findAllByIsDeletedIsNull();

    Page<Gateway> findAllByIsDeletedIsNull(Pageable pageable);

    Gateway findByIsDeletedIsNullAndGatewayId(Long id);

    boolean existsByIsDeletedIsNullAndGatewayId(Long id);
}
