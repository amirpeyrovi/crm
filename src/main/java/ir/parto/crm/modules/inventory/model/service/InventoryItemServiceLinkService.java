package ir.parto.crm.modules.inventory.model.service;

import ir.parto.crm.modules.inventory.model.entity.InventoryItemServiceLink;
import ir.parto.crm.modules.inventory.model.repository.InventoryItemServiceLinkRepository;
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
public class InventoryItemServiceLinkService implements ServiceInterface<InventoryItemServiceLink> {
    private InventoryItemServiceLinkRepository inventoryItemServiceLinkRepository;

    @Autowired
    public InventoryItemServiceLinkService(InventoryItemServiceLinkRepository inventoryItemServiceLinkRepository) {
        this.inventoryItemServiceLinkRepository = inventoryItemServiceLinkRepository;
    }

    @Override
    @Transactional
    public InventoryItemServiceLink addNewItem(InventoryItemServiceLink inventoryItemServiceLink) {
        inventoryItemServiceLink.setCreatedBy("");
        return this.inventoryItemServiceLinkRepository.save(inventoryItemServiceLink);
    }

    @Override
    public InventoryItemServiceLink updateItem(InventoryItemServiceLink inventoryItemServiceLink) throws InvocationTargetException, IllegalAccessException {
        InventoryItemServiceLink exist = this.inventoryItemServiceLinkRepository.findByIsDeletedIsNullAndInventoryServiceLinkId(inventoryItemServiceLink.getInventoryServiceLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, inventoryItemServiceLink);
        exist.setUpdatedBy("");
        return this.inventoryItemServiceLinkRepository.save(exist);
    }

    @Override
    public InventoryItemServiceLink deleteItem(InventoryItemServiceLink inventoryItemServiceLink) {
        InventoryItemServiceLink exist = this.inventoryItemServiceLinkRepository.findByIsDeletedIsNullAndInventoryServiceLinkId(inventoryItemServiceLink.getInventoryServiceLinkId());
        exist.setIsDeleted(1);
        exist.setDeletedBy("");
        exist.setDeletedDate(LocalDateTime.now());
        return this.inventoryItemServiceLinkRepository.save(exist);
    }

    @Override
    public List<InventoryItemServiceLink> findAllItem() {
        return this.inventoryItemServiceLinkRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<InventoryItemServiceLink> findAllItem(Pageable pageable) {
        return this.inventoryItemServiceLinkRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<InventoryItemServiceLink> findAllItemWithDeleted(Pageable pageable) {
        return this.inventoryItemServiceLinkRepository.findAll(pageable);
    }

    @Override
    public InventoryItemServiceLink findOne(InventoryItemServiceLink inventoryItemServiceLink) {
        return this.inventoryItemServiceLinkRepository.findByIsDeletedIsNullAndInventoryServiceLinkId(inventoryItemServiceLink.getInventoryServiceLinkId());
    }

    @Override
    public InventoryItemServiceLink findById(Long id) {
        return this.inventoryItemServiceLinkRepository.findByIsDeletedIsNullAndInventoryServiceLinkId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.inventoryItemServiceLinkRepository.existsByIsDeletedIsNullAndInventoryServiceLinkId(id);
    }
}
