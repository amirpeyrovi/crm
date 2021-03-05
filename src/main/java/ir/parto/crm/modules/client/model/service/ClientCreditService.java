package ir.parto.crm.modules.client.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.client.model.entity.ClientCredit;
import ir.parto.crm.modules.client.model.repository.ClientCreditRepository;
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
public class ClientCreditService implements ServiceInterface<ClientCredit> {
    private ClientCreditRepository clientCreditRepository;

    @Autowired
    public ClientCreditService(ClientCreditRepository clientCreditRepository) {
        this.clientCreditRepository = clientCreditRepository;
    }

    @Override
    @Transactional
    public ClientCredit addNewItem(ClientCredit clientCredit) {
        clientCredit.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        clientCredit.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientCreditRepository.save(clientCredit);
    }

    @Override
    @Transactional
    public ClientCredit updateItem(ClientCredit clientCredit) throws InvocationTargetException, IllegalAccessException {
        ClientCredit exist = this.clientCreditRepository.getOne(clientCredit.getClientCreditId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, clientCredit);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientCreditRepository.save(exist);
    }

    @Override
    @Transactional
    public ClientCredit deleteItem(ClientCredit clientCredit) {
        ClientCredit exist = this.clientCreditRepository.findByIsDeletedIsNullAndClientCreditId(clientCredit.getClientCreditId());
        exist.setIsDeleted(1);
        exist.setDeletedAt(LocalDateTime.now());
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientCreditRepository.save(exist);
    }

    @Override
    public List<ClientCredit> findAllItem() {
        return this.clientCreditRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ClientCredit> findAllItem(Pageable pageable) {
        return this.clientCreditRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ClientCredit> findAllItemWithDeleted(Pageable pageable) {
        return this.clientCreditRepository.findAll(pageable);
    }

    @Override
    public ClientCredit findOne(ClientCredit clientCredit) {
        return this.clientCreditRepository.findByIsDeletedIsNullAndClientCreditId(clientCredit.getClientCreditId());
    }

    @Override
    public ClientCredit findById(Long id) {
        return this.clientCreditRepository.findByIsDeletedIsNullAndClientCreditId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.clientCreditRepository.existsByIsDeletedIsNullAndClientCreditId(id);
    }
}
