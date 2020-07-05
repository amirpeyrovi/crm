package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadioServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteRadioServiceLinkRepository extends JpaRepository<PopSiteRadioServiceLink, Long>, RepositoryInterface<PopSiteRadioServiceLink> {
    List<PopSiteRadioServiceLink> findAllByIsDeletedIsNull();

    Page<PopSiteRadioServiceLink> findAllByIsDeletedIsNull(Pageable pageable);

    PopSiteRadioServiceLink findByIsDeletedIsNullAndRadioServiceLinkId(Long id);

    boolean existsByIsDeletedIsNullAndRadioServiceLinkId(Long id);
}
