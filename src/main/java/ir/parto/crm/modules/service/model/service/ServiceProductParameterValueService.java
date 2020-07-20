package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.ServiceProductParameterValue;
import ir.parto.crm.modules.service.model.repository.ServiceProductParameterValueRepository;
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
public class ServiceProductParameterValueService implements ServiceInterface<ServiceProductParameterValue> {
    private ServiceProductParameterValueRepository serviceProductParameterValueRepository;

    @Autowired
    public ServiceProductParameterValueService(ServiceProductParameterValueRepository serviceProductParameterValueRepository) {
        this.serviceProductParameterValueRepository = serviceProductParameterValueRepository;
    }

    @Override
    @Transactional
    public ServiceProductParameterValue addNewItem(ServiceProductParameterValue serviceProductParameterValue) {
        serviceProductParameterValue.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serviceProductParameterValueRepository.save(serviceProductParameterValue);
    }

    @Override
    @Transactional
    public ServiceProductParameterValue updateItem(ServiceProductParameterValue serviceProductParameterValue) throws InvocationTargetException, IllegalAccessException {
        ServiceProductParameterValue exist = this.serviceProductParameterValueRepository.findByIsDeletedIsNullAndServiceProductParameterValueId(serviceProductParameterValue.getServiceProductParameterValueId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, serviceProductParameterValue);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serviceProductParameterValueRepository.save(exist);
    }

    @Override
    @Transactional
    public ServiceProductParameterValue deleteItem(ServiceProductParameterValue serviceProductParameterValue) {
        ServiceProductParameterValue exist = this.serviceProductParameterValueRepository.findByIsDeletedIsNullAndServiceProductParameterValueId(serviceProductParameterValue.getServiceProductParameterValueId());
        this.serviceProductParameterValueRepository.delete(serviceProductParameterValue);
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.serviceProductParameterValueRepository.save(exist);
    }

    @Override
    public List<ServiceProductParameterValue> findAllItem() {
        return this.serviceProductParameterValueRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ServiceProductParameterValue> findAllItem(Pageable pageable) {
        return this.serviceProductParameterValueRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ServiceProductParameterValue> findAllItemWithDeleted(Pageable pageable) {
        return this.serviceProductParameterValueRepository.findAll(pageable);
    }

    @Override
    public ServiceProductParameterValue findOne(ServiceProductParameterValue serviceProductParameterValue) {
        return this.serviceProductParameterValueRepository.findByIsDeletedIsNullAndServiceProductParameterValueId(serviceProductParameterValue.getServiceProductParameterValueId());
    }

    @Override
    public ServiceProductParameterValue findById(Long id) {
        return this.serviceProductParameterValueRepository.findByIsDeletedIsNullAndServiceProductParameterValueId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceProductParameterValueRepository.existsByIsDeletedIsNullAndServiceProductParameterValueId(id);
    }
}
