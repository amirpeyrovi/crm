package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.AdminLog;
import ir.parto.crm.modules.admin.model.repository.AdminLogRepository;
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
public class AdminLogService implements ServiceInterface<AdminLog> {
    private AdminLogRepository adminLogRepository;

    @Autowired
    public AdminLogService(AdminLogRepository adminLogRepository) {
        this.adminLogRepository = adminLogRepository;
    }

    @Override
    @Transactional
    public AdminLog addNewItem(AdminLog adminLog) {
        return this.adminLogRepository.save(adminLog);
    }

    @Override
    @Transactional
    public AdminLog updateItem(AdminLog adminLog) throws InvocationTargetException, IllegalAccessException {
        AdminLog exist = this.adminLogRepository.getOne(adminLog.getAdminLogId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminLog);
        return this.adminLogRepository.save(exist);
    }

    @Override
    @Transactional
    public List<AdminLog> deleteItem(AdminLog adminLog) {
        this.adminLogRepository.delete(adminLog);
        return this.adminLogRepository.findAll();
    }

    @Override
    public List<AdminLog> findAllItem() {
        return this.adminLogRepository.findAll();
    }

    @Override
    public Page<AdminLog> findAllItem(Pageable pageable) {
        return this.adminLogRepository.findAll(pageable);
    }

    @Override
    public AdminLog findOne(AdminLog adminLog) {
        return this.adminLogRepository.getOne(adminLog.getAdminLogId());
    }

    @Override
    public AdminLog findById(Long id) {
        if (this.adminLogRepository.existsById(id)) {
            return this.adminLogRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminLogRepository.existsById(id);
    }
}
