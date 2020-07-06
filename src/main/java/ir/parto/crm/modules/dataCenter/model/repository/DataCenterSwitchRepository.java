package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitch;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterSwitchRepository extends JpaRepository<DataCenterSwitch, Long>, RepositoryInterface<DataCenterSwitch> {
    List<DataCenterSwitch> findAllByIsDeletedIsNull();

    Page<DataCenterSwitch> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterSwitch findByIsDeletedIsNullAndSwitchId(Long id);

    boolean existsByIsDeletedIsNullAndSwitchId(Long id);
}
