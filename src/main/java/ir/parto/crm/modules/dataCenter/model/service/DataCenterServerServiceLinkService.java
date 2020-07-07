package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServerServiceLink;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterServerServiceLinkRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.validation.constraints.AssertTrue;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataCenterServerServiceLinkService implements ServiceInterface<DataCenterServerServiceLink> {
    private DataCenterServerServiceLinkRepository dataCenterServerServiceLinkRepository;

    @Autowired
    public DataCenterServerServiceLinkService(DataCenterServerServiceLinkRepository dataCenterServerServiceLinkRepository) {
        this.dataCenterServerServiceLinkRepository = dataCenterServerServiceLinkRepository;
    }

    @Override
    @Transactional
    public DataCenterServerServiceLink addNewItem(DataCenterServerServiceLink dataCenterServerServiceLink) {
        dataCenterServerServiceLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerServiceLinkRepository.save(dataCenterServerServiceLink);
    }

    @Override
    @Transactional
    public DataCenterServerServiceLink updateItem(DataCenterServerServiceLink dataCenterServerServiceLink) throws InvocationTargetException, IllegalAccessException {
        DataCenterServerServiceLink exist = this.dataCenterServerServiceLinkRepository.findByIsDeletedIsNullAndServerServiceLinkId(dataCenterServerServiceLink.getServerServiceLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterServerServiceLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerServiceLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterServerServiceLink deleteItem(DataCenterServerServiceLink dataCenterServerServiceLink) {
        DataCenterServerServiceLink exist = this.dataCenterServerServiceLinkRepository.findByIsDeletedIsNullAndServerServiceLinkId(dataCenterServerServiceLink.getServerServiceLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterServerServiceLinkRepository.save(exist);
    }

    @Override
    public List<DataCenterServerServiceLink> findAllItem() {
        return this.dataCenterServerServiceLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterServerServiceLink> findAllItem(Pageable pageable) {
        return this.dataCenterServerServiceLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterServerServiceLink> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterServerServiceLinkRepository.findAll(pageable);
    }

    @Override
    public DataCenterServerServiceLink findOne(DataCenterServerServiceLink dataCenterServerServiceLink) {
        return this.dataCenterServerServiceLinkRepository.findByIsDeletedIsNullAndServerServiceLinkId(dataCenterServerServiceLink.getServerServiceLinkId());
    }

    @Override
    public DataCenterServerServiceLink findById(Long id) {
        return this.dataCenterServerServiceLinkRepository.findByIsDeletedIsNullAndServerServiceLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterServerServiceLinkRepository.existsByIsDeletedIsNullAndServerServiceLinkId(id);
    }
}
