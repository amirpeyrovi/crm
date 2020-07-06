package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterSwitchServiceLink;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterSwitchServiceLinkRepository;
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
public class DataCenterSwitchServiceLinkService implements ServiceInterface<DataCenterSwitchServiceLink> {
    private DataCenterSwitchServiceLinkRepository dataCenterSwitchServiceLinkRepository;

    @Autowired
    public DataCenterSwitchServiceLinkService(DataCenterSwitchServiceLinkRepository dataCenterSwitchServiceLinkRepository) {
        this.dataCenterSwitchServiceLinkRepository = dataCenterSwitchServiceLinkRepository;
    }

    @Override
    @Transactional
    public DataCenterSwitchServiceLink addNewItem(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        dataCenterSwitchServiceLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterSwitchServiceLinkRepository.save(dataCenterSwitchServiceLink);
    }

    @Override
    public DataCenterSwitchServiceLink updateItem(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) throws InvocationTargetException, IllegalAccessException {
        DataCenterSwitchServiceLink exist = this.dataCenterSwitchServiceLinkRepository.findByIsDeletedIsNullAndSwitchServiceLinkId(dataCenterSwitchServiceLink.getSwitchServiceLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterSwitchServiceLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterSwitchServiceLinkRepository.save(exist);
    }

    @Override
    public DataCenterSwitchServiceLink deleteItem(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        DataCenterSwitchServiceLink exist = this.dataCenterSwitchServiceLinkRepository.findByIsDeletedIsNullAndSwitchServiceLinkId(dataCenterSwitchServiceLink.getSwitchServiceLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterSwitchServiceLinkRepository.save(exist);
    }

    @Override
    public List<DataCenterSwitchServiceLink> findAllItem() {
        return this.dataCenterSwitchServiceLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterSwitchServiceLink> findAllItem(Pageable pageable) {
        return this.dataCenterSwitchServiceLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterSwitchServiceLink> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterSwitchServiceLinkRepository.findAll(pageable);
    }

    @Override
    public DataCenterSwitchServiceLink findOne(DataCenterSwitchServiceLink dataCenterSwitchServiceLink) {
        return this.dataCenterSwitchServiceLinkRepository.findByIsDeletedIsNullAndSwitchServiceLinkId(dataCenterSwitchServiceLink.getSwitchServiceLinkId());
    }

    @Override
    public DataCenterSwitchServiceLink findById(Long id) {
        return this.dataCenterSwitchServiceLinkRepository.findByIsDeletedIsNullAndSwitchServiceLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterSwitchServiceLinkRepository.existsByIsDeletedIsNullAndSwitchServiceLinkId(id);
    }
}
