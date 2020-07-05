package ir.parto.crm.modules.client.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
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
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateItem(Client client) throws InvocationTargetException, IllegalAccessException {
        Client exist = this.clientRepository.getOne(client.getClientId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, client);
        return this.clientRepository.save(exist);
    }

    @Override
    @Transactional
    public Client deleteItem(Client client) {
        Principal authentication =  SecurityContextHolder.getContext().getAuthentication();
        client.setIsDeleted(1);
        client.setDeletedAt(LocalDateTime.now());
        client.setDeletedBy(authentication.getName());
        this.clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> findAllItem() {
        return this.clientRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Client> findAllItem(Pageable pageable) {
//        return this.clientRepository.findAll(pageable);
        return this.clientRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Client> findAllItemWithDeleted(Pageable pageable) {
        return this.findAllItemWithDeleted(pageable);
    }

    @Override
    public Client findOne(Client client) {
        return this.clientRepository.getOne(client.getClientId());
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
