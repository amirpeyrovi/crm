package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigValue;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterServerConfigValueRepository;
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
public class DataCenterServerConfigValueService implements ServiceInterface<DataCenterServerConfigValue> {
    private DataCenterServerConfigValueRepository dataCenterServerConfigValueRepository;

    @Autowired
    public DataCenterServerConfigValueService(DataCenterServerConfigValueRepository dataCenterServerConfigValueRepository) {
        this.dataCenterServerConfigValueRepository = dataCenterServerConfigValueRepository;
    }

    @Override
    @Transactional
    public DataCenterServerConfigValue addNewItem(DataCenterServerConfigValue dataCenterServerConfigValue) {
        dataCenterServerConfigValue.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerConfigValueRepository.save(dataCenterServerConfigValue);
    }

    @Override
    @Transactional
    public DataCenterServerConfigValue updateItem(DataCenterServerConfigValue dataCenterServerConfigValue) throws InvocationTargetException, IllegalAccessException {
        DataCenterServerConfigValue exist = this.dataCenterServerConfigValueRepository.findByIsDeletedIsNullAndConfigValueId(dataCenterServerConfigValue.getConfigId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterServerConfigValue);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerConfigValueRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterServerConfigValue deleteItem(DataCenterServerConfigValue dataCenterServerConfigValue) {
        DataCenterServerConfigValue exist = this.dataCenterServerConfigValueRepository.findByIsDeletedIsNullAndConfigValueId(dataCenterServerConfigValue.getConfigId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterServerConfigValueRepository.save(exist);
    }

    @Override
    public List<DataCenterServerConfigValue> findAllItem() {
        return this.dataCenterServerConfigValueRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterServerConfigValue> findAllItem(Pageable pageable) {
        return this.dataCenterServerConfigValueRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterServerConfigValue> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterServerConfigValueRepository.findAll(pageable);
    }

    @Override
    public DataCenterServerConfigValue findOne(DataCenterServerConfigValue dataCenterServerConfigValue) {
        return this.dataCenterServerConfigValueRepository.findByIsDeletedIsNullAndConfigValueId(dataCenterServerConfigValue.getConfigId());
    }

    @Override
    public DataCenterServerConfigValue findById(Long id) {
        return this.dataCenterServerConfigValueRepository.findByIsDeletedIsNullAndConfigValueId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterServerConfigValueRepository.existsByIsDeletedIsNullAndConfigValueId(id);
    }
}
