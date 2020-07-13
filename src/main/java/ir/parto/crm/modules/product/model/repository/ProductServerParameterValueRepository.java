package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductServerParameterValueRepository extends JpaRepository<ProductServerParameterValue, Long>, RepositoryInterface<ProductServerParameterValue> {
    List<ProductServerParameterValue> findAllByIsDeletedIsNull();

    Page<ProductServerParameterValue> findAllByIsDeletedIsNull(Pageable pageable);

    Page<ProductServerParameterValue> findAllByIsDeletedIsNullAndProduct(Product product, Pageable pageable);

    Page<ProductServerParameterValue> findAllByIsDeletedIsNullAndProductAddon(ProductAddon productAddon, Pageable pageable);

    ProductServerParameterValue findByIsDeletedIsNullAndProductServerParameterId(Long id);

    boolean existsByIsDeletedIsNullAndProductServerParameterId(Long id);
}
