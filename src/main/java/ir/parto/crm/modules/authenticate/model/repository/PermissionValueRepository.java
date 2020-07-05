package ir.parto.crm.modules.authenticate.model.repository;

import ir.parto.crm.modules.authenticate.model.entity.PermissionValue;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionValueRepository extends JpaRepository<PermissionValue, Long>, RepositoryInterface<PermissionValue> {
}
