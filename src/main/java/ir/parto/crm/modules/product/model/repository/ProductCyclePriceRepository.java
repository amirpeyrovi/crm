package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;
import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCyclePriceRepository extends JpaRepository<ProductCyclePrice, Long>, RepositoryInterface<ProductCyclePrice> {
    List<ProductCyclePrice> findAllByIsDeletedIsNull();

    Page<ProductCyclePrice> findAllByIsDeletedIsNull(Pageable pageable);

    ProductCyclePrice findByIsDeletedIsNullAndProductCyclePriceId(Long id);

    boolean existsByIsDeletedIsNullAndProductCyclePriceId(Long id);
}
