package ir.parto.crm.modules.popSite.model.service;

import ir.parto.crm.modules.popSite.model.entity.PopSiteVendor;
import ir.parto.crm.modules.popSite.model.repository.PopSiteVendorRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PopSiteVendorService implements ServiceInterface<PopSiteVendor> {
    private PopSiteVendorRepository popSiteVendorRepository;

    @Autowired
    public PopSiteVendorService(PopSiteVendorRepository popSiteVendorRepository) {
        this.popSiteVendorRepository = popSiteVendorRepository;
    }

    @Override
    @Transactional
    public PopSiteVendor addNewItem(PopSiteVendor popSiteVendor) {
        return this.popSiteVendorRepository.save(popSiteVendor);
    }

    @Override
    @Transactional
    public PopSiteVendor updateItem(PopSiteVendor popSiteVendor) throws InvocationTargetException, IllegalAccessException {
        PopSiteVendor exist = this.popSiteVendorRepository.findByIsDeletedIsNullAndVendorId(popSiteVendor.getVendorId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, popSiteVendor);
        return this.popSiteVendorRepository.save(exist);
    }

    @Override
    public PopSiteVendor deleteItem(PopSiteVendor popSiteVendor) {
        PopSiteVendor exist = this.popSiteVendorRepository.findByIsDeletedIsNullAndVendorId(popSiteVendor.getVendorId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.popSiteVendorRepository.save(exist);
    }

    @Override
    public List<PopSiteVendor> findAllItem() {
        return this.popSiteVendorRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PopSiteVendor> findAllItem(Pageable pageable) {
        return this.popSiteVendorRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PopSiteVendor> findAllItemWithDeleted(Pageable pageable) {
        return this.popSiteVendorRepository.findAll(pageable);
    }

    @Override
    public PopSiteVendor findOne(PopSiteVendor popSiteVendor) {
        return this.popSiteVendorRepository.findByIsDeletedIsNullAndVendorId(popSiteVendor.getVendorId());
    }

    @Override
    public PopSiteVendor findById(Long id) {
        return this.popSiteVendorRepository.findByIsDeletedIsNullAndVendorId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.popSiteVendorRepository.existsByIsDeletedIsNullAndVendorId(id);
    }
}
