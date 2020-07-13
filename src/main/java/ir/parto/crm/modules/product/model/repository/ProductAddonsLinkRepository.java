package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAddonsLinkRepository extends JpaRepository<ProductAddonsLink, Long>, RepositoryInterface<ProductAddonsLink> {
    List<ProductAddonsLink> findAllByIsDeletedIsNull();

    Page<ProductAddonsLink> findAllByIsDeletedIsNull(Pageable pageable);

    Page<ProductAddonsLink> findAllByIsDeletedIsNullAndProduct(Product product, Pageable pageable);

    Page<ProductAddonsLink> findAllByIsDeletedIsNullAndProductAddon(ProductAddon productAddon, Pageable pageable);

    ProductAddonsLink findByIsDeletedIsNullAndProductAddonLinkId(Long id);

    boolean existsByIsDeletedIsNullAndProductAddonLinkId(Long id);
}
