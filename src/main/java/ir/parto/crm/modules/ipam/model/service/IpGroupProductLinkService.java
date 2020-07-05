package ir.parto.crm.modules.ipam.model.service;

import ir.parto.crm.modules.ipam.model.entity.IpGroupProductLink;
import ir.parto.crm.modules.ipam.model.repository.IpGroupProductLinkRepository;
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
public class IpGroupProductLinkService implements ServiceInterface<IpGroupProductLink> {
    private IpGroupProductLinkRepository ipGroupProductLinkRepository;

    @Autowired
    public IpGroupProductLinkService(IpGroupProductLinkRepository ipGroupProductLinkRepository) {
        this.ipGroupProductLinkRepository = ipGroupProductLinkRepository;
    }

    @Override
    @Transactional
    public IpGroupProductLink addNewItem(IpGroupProductLink ipGroupProductLink) {
        ipGroupProductLink.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ipGroupProductLinkRepository.save(ipGroupProductLink);
    }

    @Override
    @Transactional
    public IpGroupProductLink updateItem(IpGroupProductLink ipGroupProductLink) throws InvocationTargetException, IllegalAccessException {
        IpGroupProductLink exist = this.ipGroupProductLinkRepository.findByIsDeletedIsNullAndIpGroupProductLinkId(ipGroupProductLink.getIpGroupProductLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, ipGroupProductLink);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.ipGroupProductLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public IpGroupProductLink deleteItem(IpGroupProductLink ipGroupProductLink) {
        IpGroupProductLink exist = this.ipGroupProductLinkRepository.findByIsDeletedIsNullAndIpGroupProductLinkId(ipGroupProductLink.getIpGroupProductLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.ipGroupProductLinkRepository.save(exist);
    }

    @Override
    public List<IpGroupProductLink> findAllItem() {
        return this.ipGroupProductLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<IpGroupProductLink> findAllItem(Pageable pageable) {
        return this.ipGroupProductLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<IpGroupProductLink> findAllItemWithDeleted(Pageable pageable) {
        return this.ipGroupProductLinkRepository.findAll(pageable);
    }

    @Override
    public IpGroupProductLink findOne(IpGroupProductLink ipGroupProductLink) {
        return this.ipGroupProductLinkRepository.findByIsDeletedIsNullAndIpGroupProductLinkId(ipGroupProductLink.getIpGroupProductLinkId());
    }

    @Override
    public IpGroupProductLink findById(Long id) {
        return this.ipGroupProductLinkRepository.findByIsDeletedIsNullAndIpGroupProductLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.ipGroupProductLinkRepository.existsByIsDeletedIsNullAndIpGroupProductLinkId(id);
    }
}
