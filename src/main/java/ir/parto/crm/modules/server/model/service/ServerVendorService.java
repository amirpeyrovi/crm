package ir.parto.crm.modules.server.model.service;

import ir.parto.crm.modules.server.model.entity.ServerVendor;
import ir.parto.crm.modules.server.model.repository.ServerVendorRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import ir.parto.crm.utils.transientObject.ServerVendorResponse;
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
public class ServerVendorService implements ServiceInterface<ServerVendor> {
    private ServerVendorRepository serverVendorRepository;

    @Autowired
    public ServerVendorService(ServerVendorRepository serverVendorRepository) {
        this.serverVendorRepository = serverVendorRepository;
    }

    @Override
    @Transactional
    public ServerVendor addNewItem(ServerVendor serverVendor) {
        serverVendor.setTitle(serverVendor.getTitle().trim());
        serverVendor.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.serverVendorRepository.save(serverVendor);
    }

    @Override
    @Transactional
    public ServerVendor updateItem(ServerVendor serverVendor) throws InvocationTargetException, IllegalAccessException {
        ServerVendor exist = this.serverVendorRepository.findByIsDeletedIsNullAndServerVendorId(serverVendor.getServerVendorId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        exist.setTitle(serverVendor.getTitle().trim());
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        myBeanCopy.copyProperties(exist, serverVendor);
        return this.serverVendorRepository.save(exist);

    }

    @Override
    @Transactional
    public ServerVendor deleteItem(ServerVendor serverVendor) {
        ServerVendor exist = this.serverVendorRepository.findByIsDeletedIsNullAndServerVendorId(serverVendor.getServerVendorId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedAt(LocalDateTime.now());
        return this.serverVendorRepository.save(exist);
    }

    @Override
    public List<ServerVendor> findAllItem() {
        return this.serverVendorRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ServerVendor> findAllItem(Pageable pageable) {
        return this.serverVendorRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ServerVendor> findAllItemWithDeleted(Pageable pageable) {
        return this.serverVendorRepository.findAll(pageable);
    }

    @Override
    public ServerVendor findOne(ServerVendor serverVendor) {
        return this.serverVendorRepository.findByIsDeletedIsNullAndServerVendorId(serverVendor.getServerVendorId());
    }

    @Override
    public ServerVendor findById(Long id) {
        return this.serverVendorRepository.findByIsDeletedIsNullAndServerVendorId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.serverVendorRepository.existsByIsDeletedIsNullAndServerVendorId(id);
    }
}
