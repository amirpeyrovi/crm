package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfig;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterServerConfigRepository;
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
public class DataCenterServerConfigService implements ServiceInterface<DataCenterServerConfig> {
    DataCenterServerConfigRepository dataCenterServerConfigRepository;

    @Autowired
    public DataCenterServerConfigService(DataCenterServerConfigRepository dataCenterServerConfigRepository) {
        this.dataCenterServerConfigRepository = dataCenterServerConfigRepository;
    }

    @Override
    @Transactional
    public DataCenterServerConfig addNewItem(DataCenterServerConfig dataCenterServerConfig) {
        dataCenterServerConfig.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerConfigRepository.save(dataCenterServerConfig);
    }

    @Override
    @Transactional
    public DataCenterServerConfig updateItem(DataCenterServerConfig dataCenterServerConfig) throws InvocationTargetException, IllegalAccessException {
        DataCenterServerConfig exist = this.dataCenterServerConfigRepository.findByIsDeletedIsNullAndConfigId(dataCenterServerConfig.getConfigId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterServerConfig);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerConfigRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterServerConfig deleteItem(DataCenterServerConfig dataCenterServerConfig) {
        DataCenterServerConfig exist = this.dataCenterServerConfigRepository.findByIsDeletedIsNullAndConfigId(dataCenterServerConfig.getConfigId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterServerConfigRepository.save(exist);
    }

    @Override
    public List<DataCenterServerConfig> findAllItem() {
        return this.dataCenterServerConfigRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterServerConfig> findAllItem(Pageable pageable) {
        return this.dataCenterServerConfigRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterServerConfig> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterServerConfigRepository.findAll(pageable);
    }

    @Override
    public DataCenterServerConfig findOne(DataCenterServerConfig dataCenterServerConfig) {
        return this.dataCenterServerConfigRepository.findByIsDeletedIsNullAndConfigId(dataCenterServerConfig.getConfigId());
    }

    @Override
    public DataCenterServerConfig findById(Long id) {
        return this.dataCenterServerConfigRepository.findByIsDeletedIsNullAndConfigId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterServerConfigRepository.existsByIsDeletedIsNullAndConfigId(id);
    }
}
