package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.repository.AdminPermissionRepository;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
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
public class AdminPermissionService implements ServiceInterface<AdminPermission> {
    private AdminPermissionRepository adminPermissionRepository;

    @Autowired
    public AdminPermissionService(AdminPermissionRepository adminPermissionRepository) {
        this.adminPermissionRepository = adminPermissionRepository;
    }

    @Override
    @Transactional
    public AdminPermission addNewItem(AdminPermission adminPermission) {
        adminPermission.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminPermissionRepository.save(adminPermission);
    }

    @Override
    @Transactional
    public AdminPermission updateItem(AdminPermission adminPermission) throws InvocationTargetException, IllegalAccessException {
        AdminPermission exist = this.adminPermissionRepository.findByIsDeletedIsNullAndPermissionId(adminPermission.getPermissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminPermission);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminPermissionRepository.save(exist);
    }

    @Override
    @Transactional
    public AdminPermission deleteItem(AdminPermission adminPermission) {
        AdminPermission exist = this.adminPermissionRepository.findByIsDeletedIsNullAndPermissionId(adminPermission.getPermissionId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.adminPermissionRepository.save(exist);
    }

    @Override
    public List<AdminPermission> findAllItem() {
        return this.adminPermissionRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<AdminPermission> findAllItem(Pageable pageable) {
        return this.adminPermissionRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<AdminPermission> findAllItemWithDeleted(Pageable pageable) {
        return this.adminPermissionRepository.findAll(pageable);
    }

    @Override
    public AdminPermission findOne(AdminPermission adminPermission) {
        return this.adminPermissionRepository.findByIsDeletedIsNullAndPermissionId(adminPermission.getPermissionId());
    }

    @Override
    public AdminPermission findById(Long id) {
        return this.adminPermissionRepository.findByIsDeletedIsNullAndPermissionId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminPermissionRepository.existsByIsDeletedIsNullAndPermissionId(id);
    }

    public AdminPermission findByTitle(String perms) {
        return this.adminPermissionRepository.findByTitle(perms);

    }
}
