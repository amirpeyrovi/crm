package ir.parto.crm.modules.inventory.model.repository;

import ir.parto.crm.modules.inventory.model.entity.InventoryItemServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemServiceLinkRepository extends JpaRepository<InventoryItemServiceLink, Long>, RepositoryInterface<InventoryItemServiceLink> {
    List<InventoryItemServiceLink> findAllByIsDeletedIsNull();

    Page<InventoryItemServiceLink> findAllByIsDeletedIsNull(Pageable pageable);

    InventoryItemServiceLink findByIsDeletedIsNullAndInventoryServiceLinkId(Long id);

    boolean existsByIsDeletedIsNullAndInventoryServiceLinkId(Long id);
}
