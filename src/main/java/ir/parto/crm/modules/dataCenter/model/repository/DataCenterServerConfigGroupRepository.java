package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterServerConfigGroupRepository extends JpaRepository<DataCenterServerConfigGroup, Long>, RepositoryInterface<DataCenterServerConfigGroup> {
    List<DataCenterServerConfigGroup> findAllByIsDeletedIsNull();

    Page<DataCenterServerConfigGroup> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterServerConfigGroup findByIsDeletedIsNullAndConfigGroupId(Long id);

    boolean existsByIsDeletedIsNullAndConfigGroupId(Long id);
}
