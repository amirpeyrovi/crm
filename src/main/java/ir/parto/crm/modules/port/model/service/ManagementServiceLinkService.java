package ir.parto.crm.modules.port.model.service;

import ir.parto.crm.modules.port.model.entity.ManagementServiceLink;
import ir.parto.crm.modules.port.model.repository.ManagementServiceLinkRepository;
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
public class ManagementServiceLinkService implements ServiceInterface<ManagementServiceLink> {
    private ManagementServiceLinkRepository managementServiceLinkRepository;

    @Autowired
    public ManagementServiceLinkService(ManagementServiceLinkRepository managementServiceLinkRepository) {
        this.managementServiceLinkRepository = managementServiceLinkRepository;
    }

    @Override
    @Transactional
    public ManagementServiceLink addNewItem(ManagementServiceLink managementServiceLink) {
        managementServiceLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.managementServiceLinkRepository.save(managementServiceLink);
    }

    @Override
    @Transactional
    public ManagementServiceLink updateItem(ManagementServiceLink managementServiceLink) throws InvocationTargetException, IllegalAccessException {
        ManagementServiceLink exist = this.managementServiceLinkRepository.findByIsDeletedIsNullAndManagementServiceId(managementServiceLink.getManagementServiceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, managementServiceLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.managementServiceLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public ManagementServiceLink deleteItem(ManagementServiceLink managementServiceLink) {
        ManagementServiceLink exist = this.managementServiceLinkRepository.findByIsDeletedIsNullAndManagementServiceId(managementServiceLink.getManagementServiceId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.managementServiceLinkRepository.save(exist);
    }

    @Override
    public List<ManagementServiceLink> findAllItem() {
        return this.managementServiceLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ManagementServiceLink> findAllItem(Pageable pageable) {
        return this.managementServiceLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ManagementServiceLink> findAllItemWithDeleted(Pageable pageable) {
        return this.managementServiceLinkRepository.findAll(pageable);
    }

    @Override
    public ManagementServiceLink findOne(ManagementServiceLink managementServiceLink) {
        return this.managementServiceLinkRepository.findByIsDeletedIsNullAndManagementServiceId(managementServiceLink.getManagementServiceId());
    }

    @Override
    public ManagementServiceLink findById(Long id) {
        return this.managementServiceLinkRepository.findByIsDeletedIsNullAndManagementServiceId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.managementServiceLinkRepository.existsByIsDeletedIsNullAndManagementServiceId(id);
    }
}
