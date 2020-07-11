package ir.parto.crm.modules.server.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.modules.server.model.repository.ServerRepository;
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
public class ServerService implements ServiceInterface<Server> {
    private ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    @Transactional
    public Server addNewItem(Server server) {
        server.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serverRepository.save(server);
    }

    @Override
    @Transactional
    public Server updateItem(Server server) throws InvocationTargetException, IllegalAccessException {
        Server exist = this.serverRepository.findByIsDeletedIsNullAndServerId(server.getServerId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        myBeanCopy.copyProperties(exist,server);
        return this.serverRepository.save(exist);
    }

    @Override
    @Transactional
    public Server deleteItem(Server server) {
        Server exist = this.serverRepository.findByIsDeletedIsNullAndServerId(server.getServerId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.serverRepository.save(exist);
    }

    @Override
    public List<Server> findAllItem() {
        return this.serverRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Server> findAllItem(Pageable pageable) {
        return this.serverRepository.findAllByIsDeletedIsNull(pageable);

    }

    @Override
    public Page<Server> findAllItemWithDeleted(Pageable pageable) {
        return this.serverRepository.findAll(pageable);
    }

    @Override
    public Server findOne(Server server) {
        return this.serverRepository.findByIsDeletedIsNullAndServerId(server.getServerId());
    }

    @Override
    public Server findById(Long id) {
        return this.serverRepository.findByIsDeletedIsNullAndServerId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverRepository.existsByIsDeletedIsNullAndServerId(id);
    }
}
