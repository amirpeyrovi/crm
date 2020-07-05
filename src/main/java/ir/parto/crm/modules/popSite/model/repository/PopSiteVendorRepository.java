package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteVendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteVendorRepository extends JpaRepository<PopSiteVendor, Long>, RepositoryInterface<PopSiteVendor> {
    List<PopSiteVendor> findAllByIsDeletedIsNull();

    Page<PopSiteVendor> findAllByIsDeletedIsNull(Pageable pageable);

    PopSiteVendor findByIsDeletedIsNullAndVendorId(Long id);

    boolean existsByIsDeletedIsNullAndVendorId(Long id);
}
