package ir.parto.crm.modules.ipam.model.service;

import ir.parto.crm.modules.ipam.model.entity.IpAddress;
import ir.parto.crm.modules.ipam.model.repository.IpAddressRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IpAddressService implements ServiceInterface<IpAddress> {
    private IpAddressRepository ipAddressRepository;

    @Autowired
    public IpAddressService(IpAddressRepository ipAddressRepository) {
        this.ipAddressRepository = ipAddressRepository;
    }

    @Override
    @Transactional
    public IpAddress addNewItem(IpAddress ipAddress) {
        ipAddress.setCreatedBy("");
        return this.ipAddressRepository.save(ipAddress);
    }

    @Override
    @Transactional
    public IpAddress updateItem(IpAddress ipAddress) throws InvocationTargetException, IllegalAccessException {
        IpAddress exist = this.ipAddressRepository.findByIsDeletedIsNullAndIpAddressId(ipAddress.getIpAddressId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ipAddress);
        exist.setUpdatedBy("");
        return this.ipAddressRepository.save(exist);
    }

    @Override
    @Transactional
    public IpAddress deleteItem(IpAddress ipAddress) {
        IpAddress exist = this.ipAddressRepository.findByIsDeletedIsNullAndIpAddressId(ipAddress.getIpAddressId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.ipAddressRepository.save(exist);
    }

    @Override
    public List<IpAddress> findAllItem() {
        return this.ipAddressRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<IpAddress> findAllItem(Pageable pageable) {
        return this.ipAddressRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<IpAddress> findAllItemWithDeleted(Pageable pageable) {
        return this.ipAddressRepository.findAll(pageable);
    }

    @Override
    public IpAddress findOne(IpAddress ipAddress) {
        return this.ipAddressRepository.findByIsDeletedIsNullAndIpAddressId(ipAddress.getIpAddressId());
    }

    @Override
    public IpAddress findById(Long id) {
        return this.ipAddressRepository.findByIsDeletedIsNullAndIpAddressId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ipAddressRepository.existsByIsDeletedIsNullAndIpAddressId(id);
    }
}
