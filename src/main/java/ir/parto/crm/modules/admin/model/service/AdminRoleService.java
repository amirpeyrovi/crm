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
        return this.adminRoleRepository.save(adminRole);
    }

    @Override
    @Transactional
    public AdminRole updateItem(AdminRole adminRole) throws InvocationTargetException, IllegalAccessException {
        AdminRole exist = this.adminRoleRepository.getOne(adminRole.getAdminRoleId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminRole);
        return this.adminRoleRepository.save(exist);
    }

    @Override
    @Transactional
    public AdminRole deleteItem(AdminRole adminRole) {
        Admin authentication = (Admin) SecurityContextHolder.getContext().getAuthentication().getDetails();
        adminRole.setIsDeleted(1);
        adminRole.setDeletedAt(LocalDateTime.now());
        adminRole.setDeletedBy(authentication.getUsername());
        return this.adminRoleRepository.save(adminRole);
    }

    @Override
    public List<AdminRole> findAllItem() {
        return this.adminRoleRepository.findAll();
    }

    @Override
    public Page<AdminRole> findAllItem(Pageable pageable) {
        return this.adminRoleRepository.findAll(pageable);
    }

    @Override
    public Page<AdminRole> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public AdminRole findOne(AdminRole adminRole) {
        return this.adminRoleRepository.getOne(adminRole.getAdminRoleId());
    }

    @Override
    public AdminRole findById(Long id) {
        if(this.adminRoleRepository.existsById(id)){
            return this.adminRoleRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminRoleRepository.existsById(id);
    }
}
