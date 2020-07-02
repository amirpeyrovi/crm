package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import ir.parto.crm.modules.service.model.repository.ServiceAddonRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ServiceAddonService implements ServiceInterface<ServiceAddon> {
    private ServiceAddonRepository serviceAddonRepository;

    @Autowired
    public ServiceAddonService(ServiceAddonRepository serviceAddonRepository) {
        this.serviceAddonRepository = serviceAddonRepository;
    }

    @Override
    @Transactional
    public ServiceAddon addNewItem(ServiceAddon serviceAddon) {
        return this.serviceAddonRepository.save(serviceAddon);
    }

    @Override
    @Transactional
    public ServiceAddon updateItem(ServiceAddon serviceAddon) throws InvocationTargetException, IllegalAccessException {
        ServiceAddon exist = this.serviceAddonRepository.getOne(serviceAddon.getServiceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, serviceAddon);
        return this.serviceAddonRepository.save(exist);
    }

    @Override
    @Transactional
    public ServiceAddon deleteItem(ServiceAddon serviceAddon) {
        this.serviceAddonRepository.delete(serviceAddon);
        return serviceAddon;
    }

    @Override
    public List<ServiceAddon> findAllItem() {
        return this.serviceAddonRepository.findAll();
    }

    @Override
    public Page<ServiceAddon> findAllItem(Pageable pageable) {
        return this.serviceAddonRepository.findAll(pageable);
    }

    @Override
    public Page<ServiceAddon> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ServiceAddon findOne(ServiceAddon serviceAddon) {
        return this.serviceAddonRepository.getOne(serviceAddon.getServiceId());
    }

    @Override
    public ServiceAddon findById(Long id) {
        if(this.serviceAddonRepository.existsById(id)){
            return this.serviceAddonRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceAddonRepository.existsById(id);
    }
}
