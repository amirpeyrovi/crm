package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitch;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterSwitchRepository;
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
public class DataCenterSwitchService implements ServiceInterface<DataCenterSwitch> {
    private DataCenterSwitchRepository dataCenterSwitchRepository;

    @Autowired
    public DataCenterSwitchService(DataCenterSwitchRepository dataCenterSwitchRepository) {
        this.dataCenterSwitchRepository = dataCenterSwitchRepository;
    }

    @Override
    @Transactional
    public DataCenterSwitch addNewItem(DataCenterSwitch dataCenterSwitch) {
        dataCenterSwitch.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterSwitchRepository.save(dataCenterSwitch);
    }

    @Override
    @Transactional
    public DataCenterSwitch updateItem(DataCenterSwitch dataCenterSwitch) throws InvocationTargetException, IllegalAccessException {
        DataCenterSwitch exist = this.dataCenterSwitchRepository.findByIsDeletedIsNullAndSwitchId(dataCenterSwitch.getSwitchId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterSwitch);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterSwitchRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterSwitch deleteItem(DataCenterSwitch dataCenterSwitch) {
        DataCenterSwitch exist = this.dataCenterSwitchRepository.findByIsDeletedIsNullAndSwitchId(dataCenterSwitch.getSwitchId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterSwitchRepository.save(exist);
    }

    @Override
    public List<DataCenterSwitch> findAllItem() {
        return this.dataCenterSwitchRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterSwitch> findAllItem(Pageable pageable) {
        return this.dataCenterSwitchRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterSwitch> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterSwitchRepository.findAll(pageable);
    }

    @Override
    public DataCenterSwitch findOne(DataCenterSwitch dataCenterSwitch) {
        return this.dataCenterSwitchRepository.findByIsDeletedIsNullAndSwitchId(dataCenterSwitch.getSwitchId());
    }

    @Override
    public DataCenterSwitch findById(Long id) {
        return this.dataCenterSwitchRepository.findByIsDeletedIsNullAndSwitchId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterSwitchRepository.existsByIsDeletedIsNullAndSwitchId(id);
    }
}
