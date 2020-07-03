package ir.parto.crm.modules.port.model.repository;

import ir.parto.crm.modules.port.model.entity.ManagementServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagementServiceLinkRepository extends JpaRepository<ManagementServiceLink, Long>, RepositoryInterface<ManagementServiceLink> {
    List<ManagementServiceLink> findAllByIsDeletedIsNull();

    ManagementServiceLink findByIsDeletedIsNullAndManagementServiceId(Long id);
}
