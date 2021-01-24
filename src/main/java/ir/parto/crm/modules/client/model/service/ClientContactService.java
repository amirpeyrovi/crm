package ir.parto.crm.modules.client.model.service;

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
import java.security.Principal;
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
        clientContact.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientContactRepository.save(clientContact);
    }

    @Override
    @Transactional
    public ClientContact updateItem(ClientContact clientContact) throws InvocationTargetException, IllegalAccessException {
        ClientContact exist = this.clientContactRepository.findByClientContactIdAndIsDeletedIsNull(clientContact.getClientContactId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, clientContact);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.clientContactRepository.save(exist);
    }

    @Override
    @Transactional
    public ClientContact deleteItem(ClientContact clientContact) {
        ClientContact exist = this.clientContactRepository.findByClientContactIdAndIsDeletedIsNull(clientContact.getClientContactId());
        Principal authentication =  SecurityContextHolder.getContext().getAuthentication();
        clientContact.setIsDeleted(1);
        clientContact.setDeletedAt(LocalDateTime.now());
        clientContact.setDeletedBy(authentication.getName());
        this.clientContactRepository.save(exist);
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
        if(this.clientContactRepository.existsByClientContactIdAndIsDeletedIsNull(clientContact.getClientContactId())){
            return this.clientContactRepository.findByClientContactIdAndIsDeletedIsNull(clientContact.getClientContactId());
        }
        return null;
    }

    @Override
    public ClientContact findById(Long id) {
        if(this.clientContactRepository.existsByClientContactIdAndIsDeletedIsNull(id)){
            return this.clientContactRepository.findByClientContactIdAndIsDeletedIsNull(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.clientContactRepository.existsByClientContactIdAndIsDeletedIsNull(id);
    }
}
