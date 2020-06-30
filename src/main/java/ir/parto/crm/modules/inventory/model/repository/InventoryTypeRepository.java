package ir.parto.crm.modules.inventory.model.repository;

import ir.parto.crm.modules.inventory.model.entity.InventoryType;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryTypeRepository extends JpaRepository<InventoryType, Long>, RepositoryInterface<InventoryType> {
    List<InventoryType> findAllByIsDeletedIsNull();

    InventoryType findByIsDeletedIsNullAndInventoryTypeId(Long id);
}
