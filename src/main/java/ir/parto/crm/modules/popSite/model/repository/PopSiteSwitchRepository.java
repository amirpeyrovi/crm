package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteSwitch;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteSwitchRepository extends JpaRepository<PopSiteSwitch, Long>, RepositoryInterface<PopSiteSwitch> {
    List<PopSiteSwitch> findAllByIsDeletedIsNull();

    PopSiteSwitch findByIsDeletedIsNullAndSwitchId(Long id);
}
