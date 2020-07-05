package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteRadio;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteRadioRepository extends JpaRepository<PopSiteRadio, Long>, RepositoryInterface<PopSiteRadio> {
    List<PopSiteRadio> findAllByIsDeletedIsNull();

    PopSiteRadio findByIsDeletedIsNullAndRadioId(Long id);
}
