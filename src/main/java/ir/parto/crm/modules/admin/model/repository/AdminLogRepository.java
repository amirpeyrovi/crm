package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.AdminLog;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long>, RepositoryInterface<AdminLog> {
    AdminLog findByIsDeletedIsNullAndAdminLogId(Long adminLogId);

    List<AdminLog> findAllByIsDeletedIsNull();

    Page<AdminLog> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndAdminLogId(Long id);
}
