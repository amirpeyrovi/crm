package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.repository.AdminPermissionRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class AdminPermissionService implements ServiceInterface<AdminPermission> {
    private AdminPermissionRepository adminPermissionRepository;

    @Autowired
    public AdminPermissionService(AdminPermissionRepository adminPermissionRepository) {
        this.adminPermissionRepository = adminPermissionRepository;
    }

    @Override
    public AdminPermission addNewItem(AdminPermission adminPermission) {
        return this.adminPermissionRepository.save(adminPermission);
    }

    @Override
    public AdminPermission updateItem(AdminPermission adminPermission) throws InvocationTargetException, IllegalAccessException {
        AdminPermission exist = this.adminPermissionRepository.getOne(adminPermission.getAdminPermissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminPermission);
        return this.adminPermissionRepository.save(exist);
    }

    @Override
    public List<AdminPermission> deleteItem(AdminPermission adminPermission) {
        this.adminPermissionRepository.delete(adminPermission);
        return this.adminPermissionRepository.findAll();
    }

    @Override
    public List<AdminPermission> findAllItem() {
        return this.adminPermissionRepository.findAll();
    }

    @Override
    public Page<AdminPermission> findAllItem(Pageable pageable) {
        return this.adminPermissionRepository.findAll(pageable);
    }

    @Override
    public AdminPermission findOne(AdminPermission adminPermission) {
        return this.adminPermissionRepository.getOne(adminPermission.getAdminPermissionId());
    }

    @Override
    public AdminPermission findById(Long id) {
        if(this.adminPermissionRepository.existsById(id)){
            return this.adminPermissionRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminPermissionRepository.existsById(id);
    }
}
