package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenter;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterRepository;
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
public class DataCenterService implements ServiceInterface<DataCenter> {
    private DataCenterRepository dataCenterRepository;

    @Autowired
    public DataCenterService(DataCenterRepository dataCenterRepository) {
        this.dataCenterRepository = dataCenterRepository;
    }

    @Override
    @Transactional
    public DataCenter addNewItem(DataCenter dataCenter) {
        dataCenter.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterRepository.save(dataCenter);
    }

    @Override
    @Transactional
    public DataCenter updateItem(DataCenter dataCenter) throws InvocationTargetException, IllegalAccessException {
        DataCenter exist = this.dataCenterRepository.findByIsDeletedIsNullAndDataCenterId(dataCenter.getDataCenterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenter);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenter deleteItem(DataCenter dataCenter) {
        DataCenter exist = this.dataCenterRepository.findByIsDeletedIsNullAndDataCenterId(dataCenter.getDataCenterId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterRepository.save(exist);
    }

    @Override
    public List<DataCenter> findAllItem() {
        return this.dataCenterRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenter> findAllItem(Pageable pageable) {
        return this.dataCenterRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenter> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterRepository.findAll(pageable);
    }

    @Override
    public DataCenter findOne(DataCenter dataCenter) {
        return this.dataCenterRepository.findByIsDeletedIsNullAndDataCenterId(dataCenter.getDataCenterId());
    }

    @Override
    public DataCenter findById(Long id) {
        return this.dataCenterRepository.findByIsDeletedIsNullAndDataCenterId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterRepository.existsByIsDeletedIsNullAndDataCenterId(id);
    }
}
