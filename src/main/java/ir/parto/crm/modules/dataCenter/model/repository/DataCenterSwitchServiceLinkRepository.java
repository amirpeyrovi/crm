package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterSwitchServiceLinkRepository extends JpaRepository<DataCenterSwitchServiceLink, Long>, RepositoryInterface<DataCenterSwitchServiceLink> {
    List<DataCenterSwitchServiceLink> findAllByIsDeletedIsNull();

    Page<DataCenterSwitchServiceLink> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterSwitchServiceLink findByIsDeletedIsNullAndSwitchServiceLinkId(Long id);

    boolean existsByIsDeletedIsNullAndSwitchServiceLinkId(Long id);
}
