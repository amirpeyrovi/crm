package ir.parto.crm.modules.promotion.model.repository;

import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Long>, RepositoryInterface<PromotionCode> {
    List<PromotionCode> findAllByIsDeletedIsNull();

    PromotionCode findByIsDeletedIsNullAndPromotionCodeId(Long id);
}
