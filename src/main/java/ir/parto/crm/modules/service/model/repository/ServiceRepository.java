package ir.parto.crm.modules.service.model.repository;

import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>, RepositoryInterface<Service> {
    Service findByIsDeletedIsNullAndServiceId(Long serviceId);

    List<Service> findAllByIsDeletedIsNull();

    Page<Service> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndServiceId(Long id);
}
