package ir.parto.crm.modules.authenticate.model.repository;

import ir.parto.crm.modules.authenticate.model.entity.Permission;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository  extends JpaRepository<Permission, Long>, RepositoryInterface<Permission> {
    Permission findByTitle(String title);
}
