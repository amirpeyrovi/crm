package ir.parto.crm.modules.service.model.repository;

import ir.parto.crm.modules.service.model.entity.ServiceProductParameterValue;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProductParameterValueRepository extends JpaRepository<ServiceProductParameterValue, Long>, RepositoryInterface<ServiceProductParameterValue> {
    Page<ServiceProductParameterValue> findAllByIsDeletedIsNull(Pageable pageable);

    List<ServiceProductParameterValue> findAllByIsDeletedIsNull();

    ServiceProductParameterValue findByIsDeletedIsNullAndServiceProductParameterValueId(Long serviceProductHistory);

    Boolean existsByIsDeletedIsNullAndServiceProductParameterValueId(Long id);
}
