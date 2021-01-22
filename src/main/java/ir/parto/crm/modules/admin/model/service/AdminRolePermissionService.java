package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminPermission;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.entity.AdminRolePermission;
import ir.parto.crm.modules.admin.model.repository.AdminRolePermissionRepository;
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
public class AdminRolePermissionService implements ServiceInterface<AdminRolePermission> {
    private AdminRolePermissionRepository adminRolePermissionRepository;

    @Autowired
    public AdminRolePermissionService(AdminRolePermissionRepository adminRolePermissionRepository) {
        this.adminRolePermissionRepository = adminRolePermissionRepository;
    }

    @Override
    @Transactional
    public AdminRolePermission addNewItem(AdminRolePermission adminRolePermission) {
        adminRolePermission.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminRolePermissionRepository.save(adminRolePermission);
    }

    @Override
    @Transactional
    public AdminRolePermission updateItem(AdminRolePermission adminRolePermission) throws InvocationTargetException, IllegalAccessException {
        AdminRolePermission exist = this.adminRolePermissionRepository.findByIsDeletedIsNullAndAdminRolePermissionId(adminRolePermission.getAdminRolePermissionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminRolePermission);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminRolePermissionRepository.save(exist);
    }

    @Override
    @Transactional
    public AdminRolePermission deleteItem(AdminRolePermission adminRolePermission) {
        AdminRolePermission exist = this.adminRolePermissionRepository.findByIsDeletedIsNullAndAdminRolePermissionId(adminRolePermission.getAdminRolePermissionId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.adminRolePermissionRepository.save(exist);
    }

    @Override
    public List<AdminRolePermission> findAllItem() {
        return this.adminRolePermissionRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<AdminRolePermission> findAllItem(Pageable pageable) {
        return this.adminRolePermissionRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<AdminRolePermission> findAllItemWithDeleted(Pageable pageable) {
        return this.adminRolePermissionRepository.findAll(pageable);
    }

    @Override
    public AdminRolePermission findOne(AdminRolePermission adminRolePermission) {
        return this.adminRolePermissionRepository.findByIsDeletedIsNullAndAdminRolePermissionId(adminRolePermission.getAdminRolePermissionId());
    }

    @Override
    public AdminRolePermission findById(Long id) {
        return this.adminRolePermissionRepository.findByIsDeletedIsNullAndAdminRolePermissionId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminRolePermissionRepository.existsByIsDeletedIsNullAndAdminRolePermissionId(id);
    }

    public AdminRolePermission findByAdminRoleAndPermission(AdminRole adminRole, AdminPermission permission) {
        return this.adminRolePermissionRepository.findByAdminRoleAndAdminPermission(adminRole , permission);
    }

    public AdminRolePermission findByTitle(String title) {
        return this.adminRolePermissionRepository.findByTitle(title);
    }
}
