package ir.parto.crm.modules.promotion.model.repository;

import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Long>, RepositoryInterface<PromotionCode> {
    List<PromotionCode> findAllByIsDeletedIsNull();

    Page<PromotionCode> findAllByIsDeletedIsNull(Pageable pageable);

    PromotionCode findByIsDeletedIsNullAndPromotionCodeId(Long id);

    boolean existsByIsDeletedIsNullAndPromotionCodeId(Long id);
}
