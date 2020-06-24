package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long>, RepositoryInterface<AdminRole> {
}