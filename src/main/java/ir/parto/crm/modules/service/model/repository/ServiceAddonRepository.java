package ir.parto.crm.modules.service.model.repository;

import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceAddonRepository extends JpaRepository<ServiceAddon, Long>, RepositoryInterface<ServiceAddon> {
    ServiceAddon findByIsDeletedIsNullAndServiceId(Long serviceId);

    List<ServiceAddon> findAllByIsDeletedIsNull();

    Page<ServiceAddon> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndServiceId(Long id);
}
