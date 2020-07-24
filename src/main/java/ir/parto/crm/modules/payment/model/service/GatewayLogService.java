package ir.parto.crm.modules.payment.model.service;

import ir.parto.crm.modules.payment.model.entity.GatewayLog;
import ir.parto.crm.modules.payment.model.repository.GatewayLogRepository;
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
public class GatewayLogService implements ServiceInterface<GatewayLog> {
    private GatewayLogRepository gatewayLogRepository;
    @Autowired
    public GatewayLogService(GatewayLogRepository gatewayLogRepository) {
        this.gatewayLogRepository = gatewayLogRepository;
    }

    @Override
    @Transactional
    public GatewayLog addNewItem(GatewayLog gatewayLog) {
        gatewayLog.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.gatewayLogRepository.save(gatewayLog);
    }

    @Override
    @Transactional
    public GatewayLog updateItem(GatewayLog gatewayLog) throws InvocationTargetException, IllegalAccessException {
        GatewayLog exist = this.gatewayLogRepository.findByIsDeletedIsNullAndGatewayLogId(gatewayLog.getGatewayLogId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, gatewayLog);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.gatewayLogRepository.save(exist);
    }

    @Override
    @Transactional
    public GatewayLog deleteItem(GatewayLog gatewayLog) {
        GatewayLog exist = this.gatewayLogRepository.findByIsDeletedIsNullAndGatewayLogId(gatewayLog.getGatewayLogId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.gatewayLogRepository.save(exist);
    }

    @Override
    public List<GatewayLog> findAllItem() {
        return this.gatewayLogRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<GatewayLog> findAllItem(Pageable pageable) {
        return this.gatewayLogRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<GatewayLog> findAllItemWithDeleted(Pageable pageable) {
        return this.gatewayLogRepository.findAll(pageable);
    }

    @Override
    public GatewayLog findOne(GatewayLog gatewayLog) {
        return this.gatewayLogRepository.findByIsDeletedIsNullAndGatewayLogId(gatewayLog.getGatewayLogId());
    }

    @Override
    public GatewayLog findById(Long id) {
        return this.gatewayLogRepository.findByIsDeletedIsNullAndGatewayLogId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.gatewayLogRepository.existsByIsDeletedIsNullAndGatewayLogId(id);
    }
}

