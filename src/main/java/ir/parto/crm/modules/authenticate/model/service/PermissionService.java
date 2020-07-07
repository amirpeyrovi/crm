package ir.parto.crm.modules.authenticate.model.service;

import ir.parto.crm.modules.admin.model.entity.AdminPermission;
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
import java.util.List;

@Service
public class PermissionService implements ServiceInterface<AdminPermission> {
    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional
    public AdminPermission addNewItem(AdminPermission adminPermission) {
        adminPermission.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.permissionRepository.save(adminPermission);
    }

    @Override
    @Transactional
    public AdminPermission updateItem(AdminPermission adminPermission) throws InvocationTargetException, IllegalAccessException {
        AdminPermission exist = this.permissionRepository.getOne(adminPermission.getPermissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminPermission);
        return this.permissionRepository.save(exist);
    }

    @Override
    @Transactional
    public AdminPermission deleteItem(AdminPermission adminPermission) {
       return adminPermission;
    }

    @Override
    public List<AdminPermission> findAllItem() {
        return this.permissionRepository.findAll();
    }

    @Override
    public Page<AdminPermission> findAllItem(Pageable pageable) {
        return this.permissionRepository.findAll(pageable);
    }

    @Override
    public Page<AdminPermission> findAllItemWithDeleted(Pageable pageable) {
        return this.permissionRepository.findAll(pageable);
    }

    @Override
    public AdminPermission findOne(AdminPermission adminPermission) {
        return this.permissionRepository.getOne(adminPermission.getPermissionId());
    }

    @Override
    public AdminPermission findById(Long id) {
        return this.permissionRepository.getOne(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.permissionRepository.existsById(id);
    }

    public AdminPermission findByTitle(String title) {
        return this.permissionRepository.findByTitle(title);
    }
}
