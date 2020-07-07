package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitch;
import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchPort;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterSwitchPortRepository extends JpaRepository<DataCenterSwitchPort, Long>, RepositoryInterface<DataCenterSwitchPort> {
    List<DataCenterSwitchPort> findAllByIsDeletedIsNull();

    Page<DataCenterSwitchPort> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterSwitchPort findByIsDeletedIsNullAndPortId(Long id);

    boolean existsByIsDeletedIsNullAndPortId(Long id);
}
