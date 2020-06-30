package ir.parto.crm.modules.inventory.model.repository;

import ir.parto.crm.modules.inventory.model.entity.InventoryItem;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>, RepositoryInterface<InventoryItem> {
    List<InventoryItem> findAllByIsDeletedIsNull();

    InventoryItem findByIsDeletedIsNullAndInventoryItemId(Long id);
}
