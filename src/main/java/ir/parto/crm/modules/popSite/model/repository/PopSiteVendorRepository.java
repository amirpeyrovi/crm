package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteVendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteVendorRepository extends JpaRepository<PopSiteVendor, Long>, RepositoryInterface<PopSiteVendor> {
    List<PopSiteVendor> findAllByIsDeletedIsNull();

    PopSiteVendor findByIsDeletedIsNullAndVendorId(Long id);
}
