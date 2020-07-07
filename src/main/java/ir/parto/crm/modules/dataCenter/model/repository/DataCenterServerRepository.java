package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServer;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterServerRepository extends JpaRepository<DataCenterServer, Long>, RepositoryInterface<DataCenterServer> {
    List<DataCenterServer> findAllByIsDeletedIsNull();

    Page<DataCenterServer> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterServer findByIsDeletedIsNullAndServerId(Long id);

    boolean existsByIsDeletedIsNullAndServerId(Long id);
}
