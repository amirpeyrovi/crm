package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAddonRepository extends JpaRepository<ProductAddon, Long>, RepositoryInterface<ProductAddon> {
    ProductAddon findByTitleAndProductGroup(String title, ProductGroup productGroup);

    List<ProductAddon> findAllByIsDeletedIsNull();

    Page<ProductAddon> findAllByIsDeletedIsNull(Pageable pageable);

    ProductAddon findByIsDeletedIsNullAndProductAddonId(Long id);

    boolean existsByIsDeletedIsNullAndProductAddonId(Long id);
}
