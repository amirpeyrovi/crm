package ir.parto.crm.modules.dataCenter.model.service;

import ir.parto.crm.modules.dataCenter.model.entity.DataCenterServer;
import ir.parto.crm.modules.dataCenter.model.repository.DataCenterServerRepository;
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
public class DataCenterServerService implements ServiceInterface<DataCenterServer> {
    private DataCenterServerRepository dataCenterServerRepository;

    @Autowired
    public DataCenterServerService(DataCenterServerRepository dataCenterServerRepository) {
        this.dataCenterServerRepository = dataCenterServerRepository;
    }

    @Override
    @Transactional
    public DataCenterServer addNewItem(DataCenterServer dataCenterServer) {
        dataCenterServer.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerRepository.save(dataCenterServer);
    }

    @Override
    @Transactional
    public DataCenterServer updateItem(DataCenterServer dataCenterServer) throws InvocationTargetException, IllegalAccessException {
        DataCenterServer exist = this.dataCenterServerRepository.findByIsDeletedIsNullAndServerId(dataCenterServer.getServerId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, dataCenterServer);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.dataCenterServerRepository.save(exist);
    }

    @Override
    @Transactional
    public DataCenterServer deleteItem(DataCenterServer dataCenterServer) {
        DataCenterServer exist = this.dataCenterServerRepository.findByIsDeletedIsNullAndServerId(dataCenterServer.getServerId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.dataCenterServerRepository.save(exist);
    }

    @Override
    public List<DataCenterServer> findAllItem() {
        return this.dataCenterServerRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<DataCenterServer> findAllItem(Pageable pageable) {
        return this.dataCenterServerRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<DataCenterServer> findAllItemWithDeleted(Pageable pageable) {
        return this.dataCenterServerRepository.findAll(pageable);
    }

    @Override
    public DataCenterServer findOne(DataCenterServer dataCenterServer) {
        return this.dataCenterServerRepository.findByIsDeletedIsNullAndServerId(dataCenterServer.getServerId());
    }

    @Override
    public DataCenterServer findById(Long id) {
        return this.dataCenterServerRepository.findByIsDeletedIsNullAndServerId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.dataCenterServerRepository.existsByIsDeletedIsNullAndServerId(id);
    }
}
