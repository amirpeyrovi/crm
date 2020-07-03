package ir.parto.crm.modules.client.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.modules.client.model.repository.ClientExternalCodeRepository;
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
public class ClientExternalCodeService implements ServiceInterface<ClientExternalCode>{
    private ClientExternalCodeRepository clientExternalCodeRepository;

    @Autowired
    public ClientExternalCodeService(ClientExternalCodeRepository clientExternalCodeRepository) {
        this.clientExternalCodeRepository = clientExternalCodeRepository;
    }

    @Override
    @Transactional
    public ClientExternalCode addNewItem(ClientExternalCode clientExternalCode) {
        return this.clientExternalCodeRepository.save(clientExternalCode);
    }

    @Override
    @Transactional
    public ClientExternalCode updateItem(ClientExternalCode clientExternalCode) throws InvocationTargetException, IllegalAccessException {
        ClientExternalCode exist = this.clientExternalCodeRepository.getOne(clientExternalCode.getClientExternalCodeId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, clientExternalCode);
        return this.clientExternalCodeRepository.save(exist);
    }

    @Override
    @Transactional
    public ClientExternalCode deleteItem(ClientExternalCode clientExternalCode) {
        Admin authentication = (Admin) SecurityContextHolder.getContext().getAuthentication().getDetails();
        clientExternalCode.setIsDeleted(1);
        clientExternalCode.setDeletedAt(LocalDateTime.now());
        clientExternalCode.setDeletedBy(authentication.getUsername());
        this.clientExternalCodeRepository.save(clientExternalCode);
        return clientExternalCode;
    }

    @Override
    public List<ClientExternalCode> findAllItem() {
        return this.clientExternalCodeRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ClientExternalCode> findAllItem(Pageable pageable) {
        return this.clientExternalCodeRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ClientExternalCode> findAllItemWithDeleted(Pageable pageable) {
        return this.clientExternalCodeRepository.findAll(pageable);
    }

    @Override
    public ClientExternalCode findOne(ClientExternalCode clientExternalCode) {
        return this.clientExternalCodeRepository.getOne(clientExternalCode.getClientExternalCodeId());
    }

    @Override
    public ClientExternalCode findById(Long id) {
        if(this.clientExternalCodeRepository.existsById(id)){
            return this.clientExternalCodeRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.clientExternalCodeRepository.existsById(id);
    }
}
