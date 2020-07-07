package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.repository.AdminRoleRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminRoleService implements ServiceInterface<AdminRole> {
    private AdminRoleRepository adminRoleRepository;

    @Autowired
    public AdminRoleService(AdminRoleRepository adminRoleRepository) {
        this.adminRoleRepository = adminRoleRepository;
    }

    @Override
    @Transactional
    public AdminRole addNewItem(AdminRole adminRole) {
        adminRole.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminRoleRepository.save(adminRole);
    }

    @Override
    @Transactional
    public AdminRole updateItem(AdminRole adminRole) throws InvocationTargetException, IllegalAccessException {
        AdminRole exist = this.adminRoleRepository.findByIsDeletedIsNullAndAdminRoleId(adminRole.getAdminRoleId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminRole);
        return this.adminRoleRepository.save(exist);
    }

    @Override
    @Transactional
    public AdminRole deleteItem(AdminRole adminRole) {
        AdminRole exist = this.adminRoleRepository.findByIsDeletedIsNullAndAdminRoleId(adminRole.getAdminRoleId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.adminRoleRepository.save(exist);
    }

    @Override
    public List<AdminRole> findAllItem() {
        return this.adminRoleRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<AdminRole> findAllItem(Pageable pageable) {
        return this.adminRoleRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<AdminRole> findAllItemWithDeleted(Pageable pageable) {
        return this.adminRoleRepository.findAll(pageable);
    }

    @Override
    public AdminRole findOne(AdminRole adminRole) {
        return this.adminRoleRepository.findByIsDeletedIsNullAndAdminRoleId(adminRole.getAdminRoleId());
    }

    @Override
    public AdminRole findById(Long id) {
        return this.adminRoleRepository.findByIsDeletedIsNullAndAdminRoleId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminRoleRepository.existsByIsDeletedIsNullAndAdminRoleId(id);
    }
}
