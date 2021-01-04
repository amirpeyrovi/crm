package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>, RepositoryInterface<ProductGroup> {
    List<ProductGroup> findAllByIsDeletedIsNull();

    Page<ProductGroup> findAllByIsDeletedIsNull(Pageable pageable);

    ProductGroup findByIsDeletedIsNullAndProductGroupId(Long id);

    boolean existsByIsDeletedIsNullAndProductGroupId(Long id);

    List<ProductGroup> findByIsDeletedIsNullAndProductGroup(ProductGroup productGroup);
}
