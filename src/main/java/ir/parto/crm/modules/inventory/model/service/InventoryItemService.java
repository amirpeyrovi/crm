package ir.parto.crm.modules.inventory.model.service;

import ir.parto.crm.modules.inventory.model.entity.InventoryItem;
import ir.parto.crm.modules.inventory.model.repository.InventoryItemRepository;
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
public class InventoryItemService implements ServiceInterface<InventoryItem> {
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    @Transactional
    public InventoryItem addNewItem(InventoryItem inventoryItem) {
        inventoryItem.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.inventoryItemRepository.save(inventoryItem);
    }

    @Override
    @Transactional
    public InventoryItem updateItem(InventoryItem inventoryItem) throws InvocationTargetException, IllegalAccessException {
        InventoryItem exist = this.inventoryItemRepository.findByIsDeletedIsNullAndInventoryItemId(inventoryItem.getInventoryItemId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, inventoryItem);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.inventoryItemRepository.save(exist);
    }

    @Override
    @Transactional
    public InventoryItem deleteItem(InventoryItem inventoryItem) {
        InventoryItem exist = this.inventoryItemRepository.findByIsDeletedIsNullAndInventoryItemId(inventoryItem.getInventoryItemId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.inventoryItemRepository.save(exist);
    }

    @Override
    public List<InventoryItem> findAllItem() {
        return this.inventoryItemRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<InventoryItem> findAllItem(Pageable pageable) {
        return this.inventoryItemRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<InventoryItem> findAllItemWithDeleted(Pageable pageable) {
        return this.inventoryItemRepository.findAll(pageable);
    }

    @Override
    public InventoryItem findOne(InventoryItem inventoryItem) {
        return this.inventoryItemRepository.findByIsDeletedIsNullAndInventoryItemId(inventoryItem.getInventoryItemId());
    }

    @Override
    public InventoryItem findById(Long id) {
        return this.inventoryItemRepository.findByIsDeletedIsNullAndInventoryItemId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.inventoryItemRepository.existsByIsDeletedIsNullAndInventoryItemId(id);
    }
}
