package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigValue;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterServerConfigValueRepository extends JpaRepository<DataCenterServerConfigValue, Long>, RepositoryInterface<DataCenterServerConfigValue> {
    List<DataCenterServerConfigValue> findAllByIsDeletedIsNull();

    DataCenterServerConfigValue findByIsDeletedIsNullAndConfigValueId(Long id);
}
