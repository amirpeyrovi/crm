package ir.parto.crm.modules.authenticate.model.service;

import ir.parto.crm.modules.authenticate.model.entity.Permission;
import ir.parto.crm.modules.authenticate.model.repository.PermissionRepository;
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
public class PermissionService implements ServiceInterface<Permission> {
    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional
    public Permission addNewItem(Permission permission) {
        permission.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.permissionRepository.save(permission);
    }

    @Override
    @Transactional
    public Permission updateItem(Permission permission) throws InvocationTargetException, IllegalAccessException {
        Permission exist = this.permissionRepository.getOne(permission.getPermissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, permission);
        return this.permissionRepository.save(exist);
    }

    @Override
    @Transactional
    public Permission deleteItem(Permission permission) {
       return permission;
    }

    @Override
    public List<Permission> findAllItem() {
        return this.permissionRepository.findAll();
    }

    @Override
    public Page<Permission> findAllItem(Pageable pageable) {
        return this.permissionRepository.findAll(pageable);
    }

    @Override
    public Page<Permission> findAllItemWithDeleted(Pageable pageable) {
        return this.permissionRepository.findAll(pageable);
    }

    @Override
    public Permission findOne(Permission permission) {
        return this.permissionRepository.getOne(permission.getPermissionId());
    }

    @Override
    public Permission findById(Long id) {
        return this.permissionRepository.getOne(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.permissionRepository.existsById(id);
    }

    public Permission findByTitle(String title) {
        return this.permissionRepository.findByTitle(title);
    }
}
