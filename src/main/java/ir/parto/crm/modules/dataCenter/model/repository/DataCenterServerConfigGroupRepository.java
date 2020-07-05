package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterServerConfigGroupRepository extends JpaRepository<DataCenterServerConfigGroup, Long>, RepositoryInterface<DataCenterServerConfigGroup> {
    List<DataCenterServerConfigGroup> findAllByIsDeletedIsNull();

    DataCenterServerConfigGroup findByIsDeletedIsNullAndConfigGroupId(Long id);
}
