package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterVendor;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterVendorRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataCenterVendorService implements ServiceInterface<DataCenterVendor> {
    private DataCenterVendorRepository dataCenterVendorRepository;

    @Autowired
    public DataCenterVendorService(DataCenterVendorRepository dataCenterVendorRepository) {
        this.dataCenterVendorRepository = dataCenterVendorRepository;
    }

    @Override
    @Transactional
    public DataCenterVendor addNewItem(DataCenterVendor dataCenterVendor) {
        dataCenterVendor.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterVendorRepository.save(dataCenterVendor);
    }

    @Override
    @Transactional
    public DataCenterVendor updateItem(DataCenterVendor dataCenterVendor) throws InvocationTargetException, IllegalAccessException {
        DataCenterVendor exist = this.dataCenterVendorRepository.findByIsDeletedIsNullAndVendorId(dataCenterVendor.getVendorId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterVendor);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterVendorRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterVendor deleteItem(DataCenterVendor dataCenterVendor) {
        DataCenterVendor exist = this.dataCenterVendorRepository.findByIsDeletedIsNullAndVendorId(dataCenterVendor.getVendorId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterVendorRepository.save(exist);
    }

    @Override
    public List<DataCenterVendor> findAllItem() {
        return this.dataCenterVendorRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterVendor> findAllItem(Pageable pageable) {
        return this.dataCenterVendorRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterVendor> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterVendorRepository.findAll(pageable);
    }

    @Override
    public DataCenterVendor findOne(DataCenterVendor dataCenterVendor) {
        return this.dataCenterVendorRepository.findByIsDeletedIsNullAndVendorId(dataCenterVendor.getVendorId());
    }

    @Override
    public DataCenterVendor findById(Long id) {
        return this.dataCenterVendorRepository.findByIsDeletedIsNullAndVendorId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterVendorRepository.existsByIsDeletedIsNullAndVendorId(id);
    }
}
