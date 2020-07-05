package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSitePort;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSitePortRepository extends JpaRepository<PopSitePort, Long>, RepositoryInterface<PopSitePort> {
    List<PopSitePort> findAllByIsDeletedIsNull();

    PopSitePort findByIsDeletedIsNullAndPortId(Long id);
}
