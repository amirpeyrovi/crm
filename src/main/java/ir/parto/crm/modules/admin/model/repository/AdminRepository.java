package ir.parto.crm.modules.admin.model.repository;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, RepositoryInterface<Admin> {
}
