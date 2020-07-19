package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterVendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterVendorRepository extends JpaRepository<DataCenterVendor, Long>, RepositoryInterface<DataCenterVendor> {
    List<DataCenterVendor> findAllByIsDeletedIsNull();

    Page<DataCenterVendor> findAllByIsDeletedIsNull(Pageable pageable);

    DataCenterVendor findByIsDeletedIsNullAndVendorId(Long id);

    boolean existsByIsDeletedIsNullAndVendorId(Long id);
    List<DataCenterVendor> findAllByIsDeletedIsNullAndVendorId(Long vendorId);
}
