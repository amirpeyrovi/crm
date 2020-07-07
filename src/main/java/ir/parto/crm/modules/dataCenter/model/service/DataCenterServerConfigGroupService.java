package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerConfigGroup;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterServerConfigGroupRepository;
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
public class DataCenterServerConfigGroupService implements ServiceInterface<DataCenterServerConfigGroup> {
    private DataCenterServerConfigGroupRepository dataCenterServerConfigGroupRepository;

    @Autowired
    public DataCenterServerConfigGroupService(DataCenterServerConfigGroupRepository dataCenterServerConfigGroupRepository) {
        this.dataCenterServerConfigGroupRepository = dataCenterServerConfigGroupRepository;
    }

    @Override
    @Transactional
    public DataCenterServerConfigGroup addNewItem(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        dataCenterServerConfigGroup.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerConfigGroupRepository.save(dataCenterServerConfigGroup);
    }

    @Override
    @Transactional
    public DataCenterServerConfigGroup updateItem(DataCenterServerConfigGroup dataCenterServerConfigGroup) throws InvocationTargetException, IllegalAccessException {
        DataCenterServerConfigGroup exist = this.dataCenterServerConfigGroupRepository.findByIsDeletedIsNullAndConfigGroupId(dataCenterServerConfigGroup.getConfigGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterServerConfigGroup);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerConfigGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterServerConfigGroup deleteItem(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        DataCenterServerConfigGroup exist = this.dataCenterServerConfigGroupRepository.findByIsDeletedIsNullAndConfigGroupId(dataCenterServerConfigGroup.getConfigGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterServerConfigGroupRepository.save(exist);
    }

    @Override
    public List<DataCenterServerConfigGroup> findAllItem() {
        return this.dataCenterServerConfigGroupRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterServerConfigGroup> findAllItem(Pageable pageable) {
        return this.dataCenterServerConfigGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterServerConfigGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterServerConfigGroupRepository.findAll(pageable);
    }

    @Override
    public DataCenterServerConfigGroup findOne(DataCenterServerConfigGroup dataCenterServerConfigGroup) {
        return this.dataCenterServerConfigGroupRepository.findByIsDeletedIsNullAndConfigGroupId(dataCenterServerConfigGroup.getConfigGroupId());
    }

    @Override
    public DataCenterServerConfigGroup findById(Long id) {
        return this.dataCenterServerConfigGroupRepository.findByIsDeletedIsNullAndConfigGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterServerConfigGroupRepository.existsByIsDeletedIsNullAndConfigGroupId(id);
    }
}
