package ir.parto.crm.modules.server.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.modules.server.model.repository.ServerParameterRepository;
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
public class ServerParameterService implements ServiceInterface<ServerParameter> {
    private ServerParameterRepository serverParameterRepository;

    @Autowired
    public ServerParameterService(ServerParameterRepository serverParameterRepository) {
        this.serverParameterRepository = serverParameterRepository;
    }

    @Override
    @Transactional
    public ServerParameter addNewItem(ServerParameter serverParameter) {
        serverParameter.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serverParameterRepository.save(serverParameter);
    }

    @Override
    @Transactional
    public ServerParameter updateItem(ServerParameter serverParameter) throws InvocationTargetException, IllegalAccessException {
        ServerParameter exist = this.serverParameterRepository.findByIsDeletedIsNullAndServerParameterId(serverParameter.getServerParameterId());
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,serverParameter);
        return this.serverParameterRepository.save(exist);
    }

    @Override
    @Transactional
    public ServerParameter deleteItem(ServerParameter serverParameter) {
        ServerParameter exist = this.serverParameterRepository.findByIsDeletedIsNullAndServerParameterId(serverParameter
                .getServerParameterId());
        exist.setIsDeleted(1);
        exist.setDeletedAt(LocalDateTime.now());
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serverParameterRepository.save(exist);
    }

    @Override
    public List<ServerParameter> findAllItem() {
        return this.serverParameterRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ServerParameter> findAllItem(Pageable pageable) {
        return this.serverParameterRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ServerParameter> findAllItemWithDeleted(Pageable pageable) {
        return this.serverParameterRepository.findAll(pageable);
    }

    @Override
    public ServerParameter findOne(ServerParameter serverParameter) {
        return this.serverParameterRepository.findByIsDeletedIsNullAndServerParameterId(serverParameter.getServerParameterId());
    }

    @Override
    public ServerParameter findById(Long id) {
        return this.serverParameterRepository.findByIsDeletedIsNullAndServerParameterId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverParameterRepository.existsByIsDeletedIsNullAndServerParameterId(id);
    }
}
