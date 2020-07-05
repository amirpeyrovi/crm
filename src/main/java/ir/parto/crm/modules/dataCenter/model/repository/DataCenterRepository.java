package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenter;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterRepository extends JpaRepository<DataCenter, Long>, RepositoryInterface<DataCenter> {
    List<DataCenter> findAllByIsDeletedIsNull();

    DataCenter findByIsDeletedIsNullAndDataCenterId(Long id);
}
