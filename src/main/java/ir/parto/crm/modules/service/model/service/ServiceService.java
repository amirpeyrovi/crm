package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.Service;
import ir.parto.crm.modules.service.model.repository.ServiceRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
        return this.serviceRepository.save(service);
    }

    @Override
    @Transactional
    public Service updateItem(Service service) throws InvocationTargetException, IllegalAccessException {
        Service exist = this.serviceRepository.getOne(service.getServiceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, service);
        return this.serviceRepository.save(exist);
    }

    @Override
    @Transactional
    public Service deleteItem(Service service) {
        this.serviceRepository.delete(service);
        return service;
    }

    @Override
    public List<Service> findAllItem() {
        return this.serviceRepository.findAll();
    }

    @Override
    public Page<Service> findAllItem(Pageable pageable) {
        return this.serviceRepository.findAll(pageable);
    }

    @Override
    public Page<Service> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Service findOne(Service service) {
        return this.serviceRepository.getOne(service.getServiceId());
    }

    @Override
    public Service findById(Long id) {
        if(this.serviceRepository.existsById(id)){
            return this.serviceRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceRepository.existsById(id);
    }
}
