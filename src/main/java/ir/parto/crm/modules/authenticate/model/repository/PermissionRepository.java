package ir.parto.crm.modules.authenticate.model.repository;

import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository  extends JpaRepository<AdminPermission, Long>, RepositoryInterface<AdminPermission> {
    AdminPermission findByTitle(String title);
}
