package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductParameterGroupRepository extends JpaRepository<ProductParameterGroup, Long>, RepositoryInterface<ProductParameterGroup> {
    List<ProductParameterGroup> findAllByIsDeletedIsNull();

    Page<ProductParameterGroup> findAllByIsDeletedIsNull(Pageable pageable);

    ProductParameterGroup findByIsDeletedIsNullAndProductParameterGroupId(Long id);

    boolean existsByIsDeletedIsNullAndProductParameterGroupId(Long id);
}
