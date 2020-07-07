package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.repository.AdminRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService implements ServiceInterface<Admin> {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public Admin addNewItem(Admin admin) {
        admin.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return this.adminRepository.save(admin);
    }

    @Override
    @Transactional
    public Admin updateItem(Admin admin) throws InvocationTargetException, IllegalAccessException {
        Admin exist = this.adminRepository.findByIsDeletedIsNullAndAdminId(admin.getAdminId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, admin);
        return this.adminRepository.save(exist);
    }

    @Override
    @Transactional
    public Admin deleteItem(Admin admin) {
        Admin exist = this.adminRepository.findByIsDeletedIsNullAndAdminId(admin.getAdminId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.adminRepository.save(exist);
    }

    @Override
    public List<Admin> findAllItem() {
        return this.adminRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<Admin> findAllItem(Pageable pageable) {
        return this.adminRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<Admin> findAllItemWithDeleted(Pageable pageable) {
        return this.adminRepository.findAll(pageable);
    }

    @Override
    public Admin findOne(Admin admin) {
        return this.adminRepository.findByIsDeletedIsNullAndAdminId(admin.getAdminId());
    }

    @Override
    public Admin findById(Long id) {
        return this.adminRepository.findByIsDeletedIsNullAndAdminId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminRepository.existsByIsDeletedIsNullAndAdminId(id);
    }
}
