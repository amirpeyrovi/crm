package ir.parto.crm.modules.server.model.service;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.repository.ServerGroupRepository;
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
public class ServerGroupService implements ServiceInterface<ServerGroup> {
    private ServerGroupRepository serverGroupRepository;

    @Autowired
    public ServerGroupService(ServerGroupRepository serverGroupRepository) {
        this.serverGroupRepository = serverGroupRepository;
    }

    @Override
    @Transactional
    public ServerGroup addNewItem(ServerGroup serverGroup) {
        serverGroup.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serverGroupRepository.save(serverGroup);
    }

    @Override
    @Transactional
    public ServerGroup updateItem(ServerGroup serverGroup) throws InvocationTargetException, IllegalAccessException {
        ServerGroup exist = this.serverGroupRepository.findByIsDeletedIsNullAndServerGroupId(serverGroup.getServerGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, serverGroup);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serverGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public ServerGroup deleteItem(ServerGroup serverGroup) {
        ServerGroup exist = this.serverGroupRepository.findByIsDeletedIsNullAndServerGroupId(serverGroup.getServerGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.serverGroupRepository.save(exist);
    }

    @Override
    public List<ServerGroup> findAllItem() {
        return this.serverGroupRepository.findAllByIsDeletedIsNull();

    }

    @Override
    public Page<ServerGroup> findAllItem(Pageable pageable) {
        return this.serverGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ServerGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.serverGroupRepository.findAll(pageable);
    }

    @Override
    public ServerGroup findOne(ServerGroup serverGroup) {
        return this.serverGroupRepository.findByIsDeletedIsNullAndServerGroupId(serverGroup.getServerGroupId());
    }

    @Override
    public ServerGroup findById(Long id) {
        return this.serverGroupRepository.findByIsDeletedIsNullAndServerGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverGroupRepository.existsByIsDeletedIsNullAndServerGroupId(id);
    }
}
