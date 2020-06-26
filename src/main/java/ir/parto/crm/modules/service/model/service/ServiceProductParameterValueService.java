package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.ServiceProductParameterValue;
import ir.parto.crm.modules.service.model.repository.ServiceProductParameterValueRepository;
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
public class ServiceProductParameterValueService implements ServiceInterface<ServiceProductParameterValue> {
    private ServiceProductParameterValueRepository serviceProductParameterValueRepository;

    @Autowired
    public ServiceProductParameterValueService(ServiceProductParameterValueRepository serviceProductParameterValueRepository) {
        this.serviceProductParameterValueRepository = serviceProductParameterValueRepository;
    }

    @Override
    @Transactional
    public ServiceProductParameterValue addNewItem(ServiceProductParameterValue serviceProductParameterValue) {
        return this.serviceProductParameterValueRepository.save(serviceProductParameterValue);
    }

    @Override
    @Transactional
    public ServiceProductParameterValue updateItem(ServiceProductParameterValue serviceProductParameterValue) throws InvocationTargetException, IllegalAccessException {
        ServiceProductParameterValue exist = this.serviceProductParameterValueRepository.getOne(serviceProductParameterValue.getServiceProductHistory());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, serviceProductParameterValue);
        return this.serviceProductParameterValueRepository.save(exist);
    }

    @Override
    @Transactional
    public List<ServiceProductParameterValue> deleteItem(ServiceProductParameterValue serviceProductParameterValue) {
        this.serviceProductParameterValueRepository.delete(serviceProductParameterValue);
        return this.serviceProductParameterValueRepository.findAll();
    }

    @Override
    public List<ServiceProductParameterValue> findAllItem() {
        return this.serviceProductParameterValueRepository.findAll();
    }

    @Override
    public Page<ServiceProductParameterValue> findAllItem(Pageable pageable) {
        return this.serviceProductParameterValueRepository.findAll(pageable);
    }

    @Override
    public ServiceProductParameterValue findOne(ServiceProductParameterValue serviceProductParameterValue) {
        return this.serviceProductParameterValueRepository.getOne(serviceProductParameterValue.getServiceProductHistory());
    }

    @Override
    public ServiceProductParameterValue findById(Long id) {
        if(this.serviceProductParameterValueRepository.existsById(id)){
            return this.serviceProductParameterValueRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceProductParameterValueRepository.existsById(id);
    }
}
