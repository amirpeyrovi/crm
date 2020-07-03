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
        return this.serverRepository.save(server);
    }

    @Override
    @Transactional
    public Server updateItem(Server server) throws InvocationTargetException, IllegalAccessException {
        Server exist = this.serverRepository.getOne(server.getServerId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,server);
        return this.serverRepository.save(exist);
    }

    @Override
    @Transactional
    public Server deleteItem(Server server) {
        Admin authentication = (Admin) SecurityContextHolder.getContext().getAuthentication().getDetails();
        server.setIsDeleted(1);
        server.setDeletedAt(LocalDateTime.now());
        server.setDeletedBy(authentication.getUsername());
        return this.serverRepository.save(server);
    }

    @Override
    public List<Server> findAllItem() {
        return this.serverRepository.findAll();
    }

    @Override
    public Page<Server> findAllItem(Pageable pageable) {
        return this.serverRepository.findAll(pageable);
    }

    @Override
    public Page<Server> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Server findOne(Server server) {
        return this.serverRepository.getOne(server.getServerId());
    }

    @Override
    public Server findById(Long id) {
        if(this.serverRepository.existsById(id)){
            return this.serverRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverRepository.existsById(id);
    }
}
