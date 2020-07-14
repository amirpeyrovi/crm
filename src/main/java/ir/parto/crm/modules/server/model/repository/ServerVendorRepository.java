package ir.parto.crm.modules.server.model.repository;

import ir.parto.crm.modules.server.model.entity.ServerVendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerVendorRepository extends JpaRepository<ServerVendor, Long>, RepositoryInterface<ServerVendor> {
    List<ServerVendor> findAllByIsDeletedIsNull();

    Page<ServerVendor> findAllByIsDeletedIsNull(Pageable pageable);

    ServerVendor findByIsDeletedIsNullAndServerVendorId(Long id);

    Boolean existsByIsDeletedIsNullAndServerVendorId(Long id);
}
