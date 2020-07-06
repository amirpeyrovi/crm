package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRolePermissionRepository extends JpaRepository<AdminRolePermission, Long>, RepositoryInterface<AdminRolePermission> {
    AdminRolePermission findByIsDeletedIsNullAndAdminRolePermissionId(Long adminRolePermissionId);

    List<AdminRolePermission> findAllByIsDeletedIsNull();

    Page<AdminRolePermission> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndAdminRolePermissionId(Long id);
}
