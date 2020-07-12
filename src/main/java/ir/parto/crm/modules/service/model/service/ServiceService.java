package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.service.model.repository.ServiceRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements ServiceInterface<Service> {
    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Transactional
    public Service addNewItem(Service service) {
        service.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serviceRepository.save(service);
    }

    @Override
    @Transactional
    public Service updateItem(Service service) throws InvocationTargetException, IllegalAccessException {
        Service exist = this.serviceRepository.findByIsDeletedIsNullAndServiceId(service.getServiceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, service);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serviceRepository.save(exist);
    }

    @Override
    @Transactional
    public Service deleteItem(Service service) {
        Service exist = this.serviceRepository.findByIsDeletedIsNullAndServiceId(service.getServiceId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.serviceRepository.save(exist);
    }

    @Override
    public List<Service> findAllItem() {
        return this.serviceRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Service> findAllItem(Pageable pageable) {
        return this.serviceRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Service> findAllItemWithDeleted(Pageable pageable) {
        return this.serviceRepository.findAll(pageable);
    }

    @Override
    public Service findOne(Service service) {
        return this.serviceRepository.findByIsDeletedIsNullAndServiceId(service.getServiceId());
    }

    @Override
    public Service findById(Long id) {
        return this.serviceRepository.findByIsDeletedIsNullAndServiceId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceRepository.existsByIsDeletedIsNullAndServiceId(id);
    }
}
