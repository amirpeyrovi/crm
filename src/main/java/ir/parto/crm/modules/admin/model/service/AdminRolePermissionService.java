package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.modules.admin.model.repository.AdminRolePermissionRepository;
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
public class AdminRolePermissionService implements ServiceInterface<AdminRolePermission> {
    private AdminRolePermissionRepository adminRolePermissionRepository;

    @Autowired
    public AdminRolePermissionService(AdminRolePermissionRepository adminRolePermissionRepository) {
        this.adminRolePermissionRepository = adminRolePermissionRepository;
    }

    @Override
    @Transactional
    public AdminRolePermission addNewItem(AdminRolePermission adminRolePermission) {
        return this.adminRolePermissionRepository.save(adminRolePermission);
    }

    @Override
    @Transactional
    public AdminRolePermission updateItem(AdminRolePermission adminRolePermission) throws InvocationTargetException, IllegalAccessException {
        AdminRolePermission exist = this.adminRolePermissionRepository.getOne(adminRolePermission.getAdminRolePermissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminRolePermission);
        return this.adminRolePermissionRepository.save(exist);
    }

    @Override
    @Transactional
    public List<AdminRolePermission> deleteItem(AdminRolePermission adminRolePermission) {
        this.adminRolePermissionRepository.delete(adminRolePermission);
        return this.adminRolePermissionRepository.findAll();
    }

    @Override
    public List<AdminRolePermission> findAllItem() {
        return this.adminRolePermissionRepository.findAll();
    }

    @Override
    public Page<AdminRolePermission> findAllItem(Pageable pageable) {
        return this.adminRolePermissionRepository.findAll(pageable);
    }

    @Override
    public AdminRolePermission findOne(AdminRolePermission adminRolePermission) {
        return this.adminRolePermissionRepository.getOne(adminRolePermission.getAdminRolePermissionId());
    }

    @Override
    public AdminRolePermission findById(Long id) {
        if(this.adminRolePermissionRepository.existsById(id)){
            return this.adminRolePermissionRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminRolePermissionRepository.existsById(id);
    }
}
