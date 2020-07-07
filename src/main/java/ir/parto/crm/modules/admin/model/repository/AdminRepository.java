package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, RepositoryInterface<Admin> {
    Admin findByUsername(String username);
    boolean existsByUsername(String username);

    Admin findByIsDeletedIsNullAndAdminId(Long adminId);

    List<Admin> findAllByIsDeletedIsNull();

    Page<Admin> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndAdminId(Long id);
}
