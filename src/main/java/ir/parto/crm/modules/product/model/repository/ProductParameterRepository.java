package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductParameter;
import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductParameterRepository extends JpaRepository<ProductParameter, Long>, RepositoryInterface<ProductParameter> {
    List<ProductParameter> findAllByIsDeletedIsNull();

    Page<ProductParameter> findAllByIsDeletedIsNull(Pageable pageable);

    Page<ProductParameter> findAllByIsDeletedIsNullAndProductParameterGroup(ProductParameterGroup productParameterGroup, Pageable pageable);

    ProductParameter findByIsDeletedIsNullAndProductParameterId(Long id);

    boolean existsByIsDeletedIsNullAndProductParameterId(Long id);
}
