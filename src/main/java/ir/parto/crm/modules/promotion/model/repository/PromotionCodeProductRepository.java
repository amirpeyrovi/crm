package ir.parto.crm.modules.promotion.model.repository;

import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.entity.PromotionCodeProduct;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionCodeProductRepository extends JpaRepository<PromotionCodeProduct, Long>, RepositoryInterface<PromotionCodeProduct> {
    List<PromotionCodeProduct> findAllByIsDeletedIsNull();

    Page<PromotionCodeProduct> findAllByIsDeletedIsNull(Pageable pageable);

    Page<PromotionCodeProduct> findAllByIsDeletedIsNullAndPromotionCode(PromotionCode promotionCode, Pageable pageable);

    PromotionCodeProduct findByIsDeletedIsNullAndPromotionCodeProductId(Long id);

    boolean existsByIsDeletedIsNullAndPromotionCodeProductId(Long id);
}
