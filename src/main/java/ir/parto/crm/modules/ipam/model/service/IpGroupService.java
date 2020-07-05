package ir.parto.crm.modules.ipam.model.service;

import ir.parto.crm.modules.ipam.model.entity.IpGroup;
import ir.parto.crm.modules.ipam.model.repository.IpGroupRepository;
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
public class IpGroupService implements ServiceInterface<IpGroup> {
    private IpGroupRepository ipGroupRepository;

    @Autowired
    public IpGroupService(IpGroupRepository ipGroupRepository) {
        this.ipGroupRepository = ipGroupRepository;
    }

    @Override
    @Transactional
    public IpGroup addNewItem(IpGroup ipGroup) {
        ipGroup.setCreatedBy("");
        return this.ipGroupRepository.save(ipGroup);
    }

    @Override
    @Transactional
    public IpGroup updateItem(IpGroup ipGroup) throws InvocationTargetException, IllegalAccessException {
        IpGroup exist = this.ipGroupRepository.findByIsDeletedIsNullAndIpGroupId(ipGroup.getIpGroupId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ipGroup);
        exist.setUpdatedBy("");
        return this.ipGroupRepository.save(exist);
    }

    @Override
    @Transactional
    public IpGroup deleteItem(IpGroup ipGroup) {
        IpGroup exist = this.ipGroupRepository.findByIsDeletedIsNullAndIpGroupId(ipGroup.getIpGroupId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.ipGroupRepository.save(exist);
    }

    @Override
    public List<IpGroup> findAllItem() {
        return this.ipGroupRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<IpGroup> findAllItem(Pageable pageable) {
        return this.ipGroupRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<IpGroup> findAllItemWithDeleted(Pageable pageable) {
        return this.ipGroupRepository.findAll(pageable);
    }

    @Override
    public IpGroup findOne(IpGroup ipGroup) {
        return this.ipGroupRepository.findByIsDeletedIsNullAndIpGroupId(ipGroup.getIpGroupId());
    }

    @Override
    public IpGroup findById(Long id) {
        return this.ipGroupRepository.findByIsDeletedIsNullAndIpGroupId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ipGroupRepository.existsByIsDeletedIsNullAndIpGroupId(id);
    }
}
