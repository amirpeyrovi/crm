package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterServerServiceLinkRepository extends JpaRepository<DataCenterServerServiceLink, Long>, RepositoryInterface<DataCenterServerServiceLink> {
    List<DataCenterServerServiceLink> findAllByIsDeletedIsNull();

    Page<DataCenterServerServiceLink> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterServerServiceLink findByIsDeletedIsNullAndServerServiceLinkId(Long id);

    boolean existsByIsDeletedIsNullAndServerServiceLinkId(Long id);
}
