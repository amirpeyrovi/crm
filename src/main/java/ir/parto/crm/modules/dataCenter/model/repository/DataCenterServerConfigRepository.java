package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfig;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterServerConfigRepository  extends JpaRepository<DataCenterServerConfig, Long>, RepositoryInterface<DataCenterServerConfig> {
    List<DataCenterServerConfig> findAllByIsDeletedIsNull();

    DataCenterServerConfig findByIsDeletedIsNullAndConfigId(Long id);
}