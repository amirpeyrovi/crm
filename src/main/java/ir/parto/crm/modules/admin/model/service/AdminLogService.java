package ir.parto.crm.modules.admin.model.service;

import ir.parto.crm.modules.admin.model.entity.AdminLog;
import ir.parto.crm.modules.admin.model.repository.AdminLogRepository;
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
public class AdminLogService implements ServiceInterface<AdminLog> {
    private AdminLogRepository adminLogRepository;

    @Autowired
    public AdminLogService(AdminLogRepository adminLogRepository) {
        this.adminLogRepository = adminLogRepository;
    }

    @Override
    @Transactional
    public AdminLog addNewItem(AdminLog adminLog)
    {
        adminLog.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminLogRepository.save(adminLog);
    }

    @Override
    @Transactional
    public AdminLog updateItem(AdminLog adminLog) throws InvocationTargetException, IllegalAccessException {
        AdminLog exist = this.adminLogRepository.findByIsDeletedIsNullAndAdminLogId(adminLog.getAdminLogId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, adminLog);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.adminLogRepository.save(exist);
    }

    @Override
    @Transactional
    public AdminLog deleteItem(AdminLog adminLog) {
        AdminLog exist = this.adminLogRepository.findByIsDeletedIsNullAndAdminLogId(adminLog.getAdminLogId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.adminLogRepository.save(exist);
    }

    @Override
    public List<AdminLog> findAllItem() {
        return this.adminLogRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<AdminLog> findAllItem(Pageable pageable) {
        return this.adminLogRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<AdminLog> findAllItemWithDeleted(Pageable pageable) {
        return this.adminLogRepository.findAll(pageable);
    }

    @Override
    public AdminLog findOne(AdminLog adminLog) {
        return this.adminLogRepository.findByIsDeletedIsNullAndAdminLogId(adminLog.getAdminLogId());
    }

    @Override
    public AdminLog findById(Long id) {
        return this.adminLogRepository.findByIsDeletedIsNullAndAdminLogId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.adminLogRepository.existsByIsDeletedIsNullAndAdminLogId(id);
    }
}
