package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterSwitchServiceLinkRepository extends JpaRepository<DataCenterSwitchServiceLink, Long>, RepositoryInterface<DataCenterSwitchServiceLink> {
    List<DataCenterSwitchServiceLink> findAllByIsDeletedIsNull();

    DataCenterSwitchServiceLink findByIsDeletedIsNullAndSwitchServiceLinkId(Long id);
}
