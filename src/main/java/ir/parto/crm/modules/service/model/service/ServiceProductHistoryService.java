package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.ServiceProductHistory;
import ir.parto.crm.modules.service.model.repository.ServiceProductHistoryRepository;
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
public class ServiceProductHistoryService implements ServiceInterface<ServiceProductHistory> {

    private ServiceProductHistoryRepository serviceProductHistoryRepository;

    @Autowired
    public ServiceProductHistoryService(ServiceProductHistoryRepository serviceProductHistoryRepository) {
        this.serviceProductHistoryRepository = serviceProductHistoryRepository;
    }

    @Override
    @Transactional
    public ServiceProductHistory addNewItem(ServiceProductHistory serviceProductHistory) {
        serviceProductHistory.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serviceProductHistoryRepository.save(serviceProductHistory);
    }

    @Override
    @Transactional
    public ServiceProductHistory updateItem(ServiceProductHistory serviceProductHistory) throws InvocationTargetException, IllegalAccessException {
        ServiceProductHistory exist = this.serviceProductHistoryRepository.findByIsDeletedIsNullAndServiceProductHistoryId(serviceProductHistory.getServiceProductHistoryId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, serviceProductHistory);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serviceProductHistoryRepository.save(exist);
    }

    @Override
    @Transactional
    public ServiceProductHistory deleteItem(ServiceProductHistory serviceProductHistory) {
        ServiceProductHistory exist = this.serviceProductHistoryRepository.findByIsDeletedIsNullAndServiceProductHistoryId(serviceProductHistory.getServiceProductHistoryId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.serviceProductHistoryRepository.save(exist);
    }

    @Override
    public List<ServiceProductHistory> findAllItem() {
        return this.serviceProductHistoryRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ServiceProductHistory> findAllItem(Pageable pageable) {
        return this.serviceProductHistoryRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ServiceProductHistory> findAllItemWithDeleted(Pageable pageable) {
        return this.serviceProductHistoryRepository.findAll(pageable);
    }

    @Override
    public ServiceProductHistory findOne(ServiceProductHistory serviceProductHistory) {
        return this.serviceProductHistoryRepository.findByIsDeletedIsNullAndServiceProductHistoryId(serviceProductHistory.getServiceProductHistoryId());
    }

    @Override
    public ServiceProductHistory findById(Long id) {
        return this.serviceProductHistoryRepository.findByIsDeletedIsNullAndServiceProductHistoryId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceProductHistoryRepository.existsByIsDeletedIsNullAndServiceProductHistoryId(id);
    }
}
