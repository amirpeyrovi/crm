package ir.parto.crm.modules.authenticate.model.service;

import ir.parto.crm.modules.authenticate.model.entity.PermissionValue;
import ir.parto.crm.modules.authenticate.model.repository.PermissionValueRepository;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class PermissionValueService  implements ServiceInterface<PermissionValue> {
    private PermissionValueRepository permissionValueRepository;

    @Autowired
    public PermissionValueService(PermissionValueRepository permissionValueRepository) {
        this.permissionValueRepository = permissionValueRepository;
    }

    @Override
    public PermissionValue addNewItem(PermissionValue permissionValue) {
        return null;
    }

    @Override
    public PermissionValue updateItem(PermissionValue permissionValue) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    @Override
    public PermissionValue deleteItem(PermissionValue permissionValue) {
        return null;
    }

    @Override
    public List<PermissionValue> findAllItem() {
        return null;
    }

    @Override
    public Page<PermissionValue> findAllItem(Pageable pageable) {
        return null;
    }

    @Override
    public Page<PermissionValue> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public PermissionValue findOne(PermissionValue permissionValue) {
        return null;
    }

    @Override
    public PermissionValue findById(Long id) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return null;
    }
}
