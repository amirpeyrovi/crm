package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterRack;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterRackRepository extends JpaRepository<DataCenterRack, Long>, RepositoryInterface<DataCenterRack> {
    List<DataCenterRack> findAllByIsDeletedIsNull();

    DataCenterRack findByIsDeletedIsNullAndRackId(Long id);
}
