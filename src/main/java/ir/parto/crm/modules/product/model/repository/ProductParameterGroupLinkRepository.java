package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductParameterGroupLinkRepository extends JpaRepository<ProductParameterGroupLink, Long>, RepositoryInterface<ProductParameterGroupLink> {
    List<ProductParameterGroupLink> findAllByIsDeletedIsNull();

    Page<ProductParameterGroupLink> findAllByIsDeletedIsNull(Pageable pageable);

    ProductParameterGroupLink findByIsDeletedIsNullAndProductParameterGroupLinkId(Long id);

    boolean existsByIsDeletedIsNullAndProductParameterGroupLinkId(Long id);
}
