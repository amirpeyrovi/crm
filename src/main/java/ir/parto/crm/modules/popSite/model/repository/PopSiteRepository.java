package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSite;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteRepository extends JpaRepository<PopSite, Long>, RepositoryInterface<PopSite> {
    List<PopSite> findAllByIsDeletedIsNull();

    PopSite findByIsDeletedIsNullAndPopSiteId(Long id);
}
