package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchPort;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterSwitchPortRepository;
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
public class DataCenterSwitchPortService implements ServiceInterface<DataCenterSwitchPort> {
    private DataCenterSwitchPortRepository dataCenterSwitchPortRepository;

    @Autowired
    public DataCenterSwitchPortService(DataCenterSwitchPortRepository dataCenterSwitchPortRepository) {
        this.dataCenterSwitchPortRepository = dataCenterSwitchPortRepository;
    }

    @Override
    @Transactional
    public DataCenterSwitchPort addNewItem(DataCenterSwitchPort dataCenterSwitchPort) {
        dataCenterSwitchPort.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterSwitchPortRepository.save(dataCenterSwitchPort);
    }

    @Override
    @Transactional
    public DataCenterSwitchPort updateItem(DataCenterSwitchPort dataCenterSwitchPort) throws InvocationTargetException, IllegalAccessException {
        DataCenterSwitchPort exist = this.dataCenterSwitchPortRepository.findByIsDeletedIsNullAndPortId(dataCenterSwitchPort.getPortId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterSwitchPort);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterSwitchPortRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterSwitchPort deleteItem(DataCenterSwitchPort dataCenterSwitchPort) {
        DataCenterSwitchPort exist = this.dataCenterSwitchPortRepository.findByIsDeletedIsNullAndPortId(dataCenterSwitchPort.getPortId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterSwitchPortRepository.save(exist);
    }

    @Override
    public List<DataCenterSwitchPort> findAllItem() {
        return this.dataCenterSwitchPortRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterSwitchPort> findAllItem(Pageable pageable) {
        return this.dataCenterSwitchPortRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterSwitchPort> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterSwitchPortRepository.findAll(pageable);
    }

    @Override
    public DataCenterSwitchPort findOne(DataCenterSwitchPort dataCenterSwitchPort) {
        return this.dataCenterSwitchPortRepository.findByIsDeletedIsNullAndPortId(dataCenterSwitchPort.getPortId());
    }

    @Override
    public DataCenterSwitchPort findById(Long id) {
        return this.dataCenterSwitchPortRepository.findByIsDeletedIsNullAndPortId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterSwitchPortRepository.existsByIsDeletedIsNullAndPortId(id);
    }
}
