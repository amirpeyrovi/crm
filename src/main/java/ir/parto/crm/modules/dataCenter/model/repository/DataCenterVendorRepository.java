package ir.parto.crm.modules.dataCenter.model.repository;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterVendor;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCenterVendorRepository extends JpaRepository<DataCenterVendor, Long>, RepositoryInterface<DataCenterVendor> {
    List<DataCenterVendor> findAllByIsDeletedIsNull();

    DataCenterVendor findByIsDeletedIsNullAndVendorId(Long id);
}
