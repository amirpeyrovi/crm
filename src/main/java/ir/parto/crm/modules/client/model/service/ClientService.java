package ir.parto.crm.modules.client.model.service;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.modules.client.model.repository.ClientRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService implements ServiceInterface<Client> {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client addNewItem(Client client) {
        client.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        client.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateItem(Client client) throws InvocationTargetException, IllegalAccessException {
        Client exist = this.clientRepository.findByClientIdAndIsDeletedIsNull(client.getClientId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, client);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientRepository.save(exist);
    }

    @Override
    @Transactional
    public Client deleteItem(Client client) {
        Client exist = this.clientRepository.findByClientIdAndIsDeletedIsNull(client.getClientId());
        Principal authentication =  SecurityContextHolder.getContext().getAuthentication();
        client.setIsDeleted(1);
        client.setDeletedAt(LocalDateTime.now());
        client.setDeletedBy(authentication.getName());
        this.clientRepository.save(exist);
        return client;
    }

    @Override
    public List<Client> findAllItem() {
        return this.clientRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Client> findAllItem(Pageable pageable) {
        return this.clientRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Client> findAllItemWithDeleted(Pageable pageable) {
        return this.clientRepository.findAll(pageable);
    }

    @Override
    public Client findOne(Client client) {
        return this.clientRepository.findByClientIdAndIsDeletedIsNull(client.getClientId());
    }

    @Override
    public Client findById(Long id) {
        if(this.clientRepository.existsByClientIdAndIsDeletedIsNull(id)){
            return this.clientRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.clientRepository.existsById(id);
    }

    public Client findByEmail(String email) {
        return this.clientRepository.findByEmailAddressAndIsDeletedIsNull(email);
    }

    public Boolean existsByIdNotDeleted(Long id) {
        return this.clientRepository.existsByClientIdAndIsDeletedIsNull(id);
    }
}
