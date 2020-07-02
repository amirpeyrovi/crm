package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.repository.AdminRepository;
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
public class AdminService implements ServiceInterface<Admin> {
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public Admin addNewItem(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    @Transactional
    public Admin updateItem(Admin admin) throws InvocationTargetException, IllegalAccessException {
        Admin exist = this.adminRepository.getOne(admin.getAdminId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, admin);
        return this.adminRepository.save(exist);
    }

    @Override
    @Transactional
    public Admin deleteItem(Admin admin) {
        this.adminRepository.delete(admin);
        return admin;
    }

    @Override
    public List<Admin> findAllItem() {
        return this.adminRepository.findAll();
    }

    @Override
    public Page<Admin> findAllItem(Pageable pageable) {
        return this.adminRepository.findAll(pageable);
    }

    @Override
    public Page<Admin> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Admin findOne(Admin admin) {
        return this.adminRepository.getOne(admin.getAdminId());
    }

    @Override
    public Admin findById(Long id) {
        if(this.adminRepository.existsById(id)){
            return this.adminRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminRepository.existsById(id);
    }
}
