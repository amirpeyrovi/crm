package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSite;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteRepository extends JpaRepository<PopSite, Long>, RepositoryInterface<PopSite> {
    List<PopSite> findAllByIsDeletedIsNull();

    Page<PopSite> findAllByIsDeletedIsNull(Pageable pageable);

    PopSite findByIsDeletedIsNullAndPopSiteId(Long id);

    boolean existsByIsDeletedIsNullAndPopSiteId(Long id);
}
