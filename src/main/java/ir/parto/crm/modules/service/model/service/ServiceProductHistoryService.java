package ir.parto.crm.modules.service.model.service;

import ir.parto.crm.modules.service.model.entity.ServiceProductHistory;
import ir.parto.crm.modules.service.model.repository.ServiceProductHistoryRepository;
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
public class ServiceProductHistoryService implements ServiceInterface<ServiceProductHistory> {

    private ServiceProductHistoryRepository serviceProductHistoryRepository;

    @Autowired
    public ServiceProductHistoryService(ServiceProductHistoryRepository serviceProductHistoryRepository) {
        this.serviceProductHistoryRepository = serviceProductHistoryRepository;
    }

    @Override
    @Transactional
    public ServiceProductHistory addNewItem(ServiceProductHistory serviceProductHistory) {
        return this.serviceProductHistoryRepository.save(serviceProductHistory);
    }

    @Override
    @Transactional
    public ServiceProductHistory updateItem(ServiceProductHistory serviceProductHistory) throws InvocationTargetException, IllegalAccessException {
        ServiceProductHistory exist = this.serviceProductHistoryRepository.getOne(serviceProductHistory.getServiceProductHistoryId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, serviceProductHistory);
        return this.serviceProductHistoryRepository.save(exist);
    }

    @Override
    @Transactional
    public ServiceProductHistory deleteItem(ServiceProductHistory serviceProductHistory) {
        this.serviceProductHistoryRepository.delete(serviceProductHistory);
        return serviceProductHistory;
    }

    @Override
    public List<ServiceProductHistory> findAllItem() {
        return this.serviceProductHistoryRepository.findAll();
    }

    @Override
    public Page<ServiceProductHistory> findAllItem(Pageable pageable) {
        return this.serviceProductHistoryRepository.findAll(pageable);
    }

    @Override
    public Page<ServiceProductHistory> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ServiceProductHistory findOne(ServiceProductHistory serviceProductHistory) {
        return this.serviceProductHistoryRepository.getOne(serviceProductHistory.getServiceProductHistoryId());
    }

    @Override
    public ServiceProductHistory findById(Long id) {
        if(this.serviceProductHistoryRepository.existsById(id)){
            return this.serviceProductHistoryRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serviceProductHistoryRepository.existsById(id);
    }
}
