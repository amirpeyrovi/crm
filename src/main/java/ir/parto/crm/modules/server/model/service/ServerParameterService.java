package ir.parto.crm.modules.server.model.service;

import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.modules.server.model.repository.ServerParameterRepository;
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
public class ServerParameterService implements ServiceInterface<ServerParameter> {
    private ServerParameterRepository serverParameterRepository;

    @Autowired
    public ServerParameterService(ServerParameterRepository serverParameterRepository) {
        this.serverParameterRepository = serverParameterRepository;
    }

    @Override
    @Transactional
    public ServerParameter addNewItem(ServerParameter serverParameter) {
        return this.serverParameterRepository.save(serverParameter);
    }

    @Override
    @Transactional
    public ServerParameter updateItem(ServerParameter serverParameter) throws InvocationTargetException, IllegalAccessException {
        ServerParameter exist = this.serverParameterRepository.getOne(serverParameter.getServerParameterId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,serverParameter);
        return this.serverParameterRepository.save(serverParameter);
    }

    @Override
    @Transactional
    public ServerParameter deleteItem(ServerParameter serverParameter) {
        this.serverParameterRepository.delete(serverParameter);
        return serverParameter;
    }

    @Override
    public List<ServerParameter> findAllItem() {
        return this.serverParameterRepository.findAll();
    }

    @Override
    public Page<ServerParameter> findAllItem(Pageable pageable) {
        return this.serverParameterRepository.findAll(pageable);
    }

    @Override
    public Page<ServerParameter> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ServerParameter findOne(ServerParameter serverParameter) {
        if(this.serverParameterRepository.existsById(serverParameter.getServerParameterId())){
            return this.serverParameterRepository.getOne(serverParameter.getServerParameterId());
        }
        return null;
    }

    @Override
    public ServerParameter findById(Long id) {
        if(this.serverParameterRepository.existsById(id)){
            return this.serverParameterRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverParameterRepository.existsById(id);
    }
}
