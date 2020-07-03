package ir.parto.crm.modules.port.model.repository;

import ir.parto.crm.modules.port.model.entity.Management;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Long>, RepositoryInterface<Management> {
    List<Management> findAllByIsDeletedIsNull();

    Management findByIsDeletedIsNullAndManagementId(Long id);
}
