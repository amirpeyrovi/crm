package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterRack;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterRackRepository extends JpaRepository<DataCenterRack, Long>, RepositoryInterface<DataCenterRack> {
    List<DataCenterRack> findAllByIsDeletedIsNull();

    Page<DataCenterRack> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterRack findByIsDeletedIsNullAndRackId(Long id);

    boolean existsByIsDeletedIsNullAndRackId(Long id);

    List<DataCenterRack> findAllByIsDeletedIsNullAndAndRackId(Long rackId);
}
