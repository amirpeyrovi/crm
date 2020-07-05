package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRack;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteRackRepository extends JpaRepository<PopSiteRack, Long>, RepositoryInterface<PopSiteRack> {
    List<PopSiteRack> findAllByIsDeletedIsNull();

    Page<PopSiteRack> findAllByIsDeletedIsNull(Pageable pageable);

    PopSiteRack findByIsDeletedIsNullAndRackId(Long id);

    boolean existsByIsDeletedIsNullAndRackId(Long id);
}
