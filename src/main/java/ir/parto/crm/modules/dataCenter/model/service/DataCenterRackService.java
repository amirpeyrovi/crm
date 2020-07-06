package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterRack;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterRackRepository;
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
public class DataCenterRackService implements ServiceInterface<DataCenterRack> {
    private DataCenterRackRepository dataCenterRackRepository;

    @Autowired
    public DataCenterRackService(DataCenterRackRepository dataCenterRackRepository) {
        this.dataCenterRackRepository = dataCenterRackRepository;
    }

    @Override
    @Transactional
    public DataCenterRack addNewItem(DataCenterRack dataCenterRack) {
        dataCenterRack.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterRackRepository.save(dataCenterRack);
    }

    @Override
    @Transactional
    public DataCenterRack updateItem(DataCenterRack dataCenterRack) throws InvocationTargetException, IllegalAccessException {
        DataCenterRack exist = this.dataCenterRackRepository.findByIsDeletedIsNullAndRackId(dataCenterRack.getRackId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterRack);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterRackRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterRack deleteItem(DataCenterRack dataCenterRack) {
        DataCenterRack exist = this.dataCenterRackRepository.findByIsDeletedIsNullAndRackId(dataCenterRack.getRackId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterRackRepository.save(exist);
    }

    @Override
    public List<DataCenterRack> findAllItem() {
        return this.dataCenterRackRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterRack> findAllItem(Pageable pageable) {
        return this.dataCenterRackRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterRack> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterRackRepository.findAll(pageable);
    }

    @Override
    public DataCenterRack findOne(DataCenterRack dataCenterRack) {
        return this.dataCenterRackRepository.findByIsDeletedIsNullAndRackId(dataCenterRack.getRackId());
    }

    @Override
    public DataCenterRack findById(Long id) {
        return this.dataCenterRackRepository.findByIsDeletedIsNullAndRackId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterRackRepository.existsByIsDeletedIsNullAndRackId(id);
    }
}
