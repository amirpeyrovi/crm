package ir.parto.crm.modules.server.model.service;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.repository.ServerGroupRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
        return this.serverGroupRepository.save(serverGroup);
    }

    @Override
    @Transactional
    public ServerGroup updateItem(ServerGroup serverGroup) throws InvocationTargetException, IllegalAccessException {
        ServerGroup exist = this.serverGroupRepository.getOne(serverGroup.getServerGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,serverGroup);
        return this.serverGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public ServerGroup deleteItem(ServerGroup serverGroup) {
        this.serverGroupRepository.delete(serverGroup);
        return serverGroup;
    }

    @Override
    public List<ServerGroup> findAllItem() {
        return this.serverGroupRepository.findAll();
    }

    @Override
    public Page<ServerGroup> findAllItem(Pageable pageable) {
        return this.serverGroupRepository.findAll(pageable);
    }

    @Override
    public Page<ServerGroup> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ServerGroup findOne(ServerGroup serverGroup) {
        if(this.serverGroupRepository.existsById(serverGroup.getServerGroupId())){
            return this.serverGroupRepository.getOne(serverGroup.getServerGroupId());
        }
        return null;
    }

    @Override
    public ServerGroup findById(Long id) {
        if(this.serverGroupRepository.existsById(id)){
            return this.serverGroupRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverGroupRepository.existsById(id);
    }
}
