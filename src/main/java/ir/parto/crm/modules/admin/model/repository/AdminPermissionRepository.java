package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPermissionRepository extends JpaRepository<AdminPermission, Long>, RepositoryInterface<AdminPermission> {
    AdminPermission findByTitle(String perms);

    AdminPermission findByIsDeletedIsNullAndPermissionId(Long permissionId);

    Page<AdminPermission> findAllByIsDeletedIsNull(Pageable pageable);

    List<AdminPermission> findAllByIsDeletedIsNull();

    Boolean existsByIsDeletedIsNullAndPermissionId(Long id);
}
