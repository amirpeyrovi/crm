package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitch;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterSwitchRepository extends JpaRepository<DataCenterSwitch, Long>, RepositoryInterface<DataCenterSwitch> {
    List<DataCenterSwitch> findAllByIsDeletedIsNull();

    DataCenterSwitch findByIsDeletedIsNullAndSwitchId(Long id);
}
