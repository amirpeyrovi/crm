package ir.parto.crm.modules.port.model.service;

import ir.parto.crm.modules.port.model.entity.Management;
import ir.parto.crm.modules.port.model.repository.ManagementRepository;
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
public class ManagementService implements ServiceInterface<Management> {
    private ManagementRepository managementRepository;

    @Autowired
    public ManagementService(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @Override
    @Transactional
    public Management addNewItem(Management management) {
        management.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.managementRepository.save(management);
    }

    @Override
    @Transactional
    public Management updateItem(Management management) throws InvocationTargetException, IllegalAccessException {
        Management exist = this.managementRepository.findByIsDeletedIsNullAndManagementId(management.getManagementId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, management);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.managementRepository.save(exist);
    }

    @Override
    @Transactional
    public Management deleteItem(Management management) {
        Management exist = this.managementRepository.findByIsDeletedIsNullAndManagementId(management.getManagementId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.managementRepository.save(exist);
    }

    @Override
    public List<Management> findAllItem() {
        return this.managementRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Management> findAllItem(Pageable pageable) {
        return this.managementRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Management> findAllItemWithDeleted(Pageable pageable) {
        return this.managementRepository.findAll(pageable);
    }

    @Override
    public Management findOne(Management management) {
        return this.managementRepository.findByIsDeletedIsNullAndManagementId(management.getManagementId());
    }

    @Override
    public Management findById(Long id) {
        return this.managementRepository.findByIsDeletedIsNullAndManagementId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.managementRepository.existsByIsDeletedIsNullAndManagementId(id);
    }
}
