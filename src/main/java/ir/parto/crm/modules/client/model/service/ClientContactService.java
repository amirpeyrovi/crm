package ir.parto.crm.modules.client.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.modules.client.model.repository.ClientContactRepository;
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
public class ClientContactService implements ServiceInterface<ClientContact> {
    private ClientContactRepository clientContactRepository;

    @Autowired
    public ClientContactService(ClientContactRepository clientContactRepository) {
        this.clientContactRepository = clientContactRepository;
    }

    @Override
    @Transactional
    public ClientContact addNewItem(ClientContact clientContact) {
        return this.clientContactRepository.save(clientContact);
    }

    @Override
    @Transactional
    public ClientContact updateItem(ClientContact clientContact) throws InvocationTargetException, IllegalAccessException {
        ClientContact exist = this.clientContactRepository.getOne(clientContact.getClientContactId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, clientContact);
        return this.clientContactRepository.save(exist);
    }

    @Override
    @Transactional
    public ClientContact deleteItem(ClientContact clientContact) {
        Admin authentication = (Admin) SecurityContextHolder.getContext().getAuthentication().getDetails();
        clientContact.setDeletedAt(LocalDateTime.now());
        clientContact.setDeletedBy(authentication.getUsername());
        clientContact.setIsDeleted(1);
        this.clientContactRepository.save(clientContact);
        return clientContact;
    }

    @Override
    public List<ClientContact> findAllItem() {
        return this.clientContactRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ClientContact> findAllItem(Pageable pageable) {
        return this.clientContactRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ClientContact> findAllItemWithDeleted(Pageable pageable) {
        return this.clientContactRepository.findAll(pageable);
    }

    @Override
    public ClientContact findOne(ClientContact clientContact) {
        if(this.clientContactRepository.existsById(clientContact.getClientContactId())){
            return this.clientContactRepository.getOne(clientContact.getClientContactId());
        }
        return null;
    }

    @Override
    public ClientContact findById(Long id) {
        if(this.clientContactRepository.existsById(id)){
            return this.clientContactRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.clientContactRepository.existsById(id);
    }
}
