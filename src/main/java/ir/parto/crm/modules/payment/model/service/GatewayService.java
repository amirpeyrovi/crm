package ir.parto.crm.modules.payment.model.service;

import ir.parto.crm.modules.payment.controller.validate.GatewayValidate;
import ir.parto.crm.modules.payment.model.entity.Gateway;
import ir.parto.crm.modules.payment.model.repository.GatewayRepository;
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
public class GatewayService implements ServiceInterface<Gateway> {

    private GatewayRepository gatewayRepository;
    @Autowired
    public GatewayService(GatewayRepository gatewayRepository) {
        this.gatewayRepository = gatewayRepository;
    }

    @Override
    @Transactional
    public Gateway addNewItem(Gateway gateway) {
        gateway.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.gatewayRepository.save(gateway);
    }

    @Override
    @Transactional
    public Gateway updateItem(Gateway gateway) throws InvocationTargetException, IllegalAccessException {
        Gateway exist = this.gatewayRepository.findByIsDeletedIsNullAndGatewayId(gateway.getGatewayId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, gateway);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.gatewayRepository.save(exist);
    }

    @Override
    @Transactional
    public Gateway deleteItem(Gateway gateway) {
        Gateway exist = this.gatewayRepository.findByIsDeletedIsNullAndGatewayId(gateway.getGatewayId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.gatewayRepository.save(exist);
    }

    @Override
    public List<Gateway> findAllItem() {
        return this.gatewayRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Gateway> findAllItem(Pageable pageable) {
        return this.gatewayRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Gateway> findAllItemWithDeleted(Pageable pageable) {
        return this.gatewayRepository.findAll(pageable);
    }

    @Override
    public Gateway findOne(Gateway gateway) {
        return this.gatewayRepository.findByIsDeletedIsNullAndGatewayId(gateway.getGatewayId());
    }

    @Override
    public Gateway findById(Long id) {
        return this.gatewayRepository.findByIsDeletedIsNullAndGatewayId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.gatewayRepository.existsByIsDeletedIsNullAndGatewayId(id);
    }
}
