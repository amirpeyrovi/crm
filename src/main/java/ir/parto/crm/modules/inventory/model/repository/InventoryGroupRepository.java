package ir.parto.crm.modules.inventory.model.repository;

import ir.parto.crm.modules.inventory.model.entity.InventoryGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryGroupRepository extends JpaRepository<InventoryGroup, Long>, RepositoryInterface<InventoryGroup> {
    List<InventoryGroup> findAllByIsDeletedIsNull();

    InventoryGroup findByIsDeletedIsNullAndInventoryGroupId(Long id);
}
