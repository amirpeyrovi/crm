package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long>, RepositoryInterface<AdminRole> {
    AdminRole findByIsDeletedIsNullAndAdminRoleId(Long adminRoleId);

    Boolean existsByIsDeletedIsNullAndAdminRoleId(Long id);

    Page<AdminRole> findAllByIsDeletedIsNull(Pageable pageable);

    List<AdminRole> findAllByIsDeletedIsNull();

    AdminRole findByTitle(String title);
}
