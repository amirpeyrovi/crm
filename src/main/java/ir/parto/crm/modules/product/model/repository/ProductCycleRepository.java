package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCycleRepository extends JpaRepository<ProductCycle, Long>, RepositoryInterface<ProductCycle> {
    List<ProductCycle> findAllByIsDeletedIsNull();

    Page<ProductCycle> findAllByIsDeletedIsNull(Pageable pageable);

    ProductCycle findByIsDeletedIsNullAndProductCycleId(Long id);

    boolean existsByIsDeletedIsNullAndProductCycleId(Long id);
}
