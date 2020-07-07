package ir.parto.crm.modules.port.model.service;

import ir.parto.crm.modules.port.model.entity.Vendor;
import ir.parto.crm.modules.port.model.repository.VendorRepository;
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
public class VendorService implements ServiceInterface<Vendor> {
    private VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    @Transactional
    public Vendor addNewItem(Vendor vendor) {
        vendor.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.vendorRepository.save(vendor);
    }

    @Override
    @Transactional
    public Vendor updateItem(Vendor vendor) throws InvocationTargetException, IllegalAccessException {
        Vendor exist = this.vendorRepository.findByIsDeletedIsNullAndVendorId(vendor.getVendorId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, vendor);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.vendorRepository.save(exist);
    }

    @Override
    @Transactional
    public Vendor deleteItem(Vendor vendor) {
        Vendor exist = this.vendorRepository.findByIsDeletedIsNullAndVendorId(vendor.getVendorId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.vendorRepository.save(exist);
    }

    @Override
    public List<Vendor> findAllItem() {
        return this.vendorRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Vendor> findAllItem(Pageable pageable) {
        return this.vendorRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Vendor> findAllItemWithDeleted(Pageable pageable) {
        return this.vendorRepository.findAll(pageable);
    }

    @Override
    public Vendor findOne(Vendor vendor) {
        return this.vendorRepository.findByIsDeletedIsNullAndVendorId(vendor.getVendorId());
    }

    @Override
    public Vendor findById(Long id) {
        return this.vendorRepository.findByIsDeletedIsNullAndVendorId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.vendorRepository.existsByIsDeletedIsNullAndVendorId(id);
    }
}
