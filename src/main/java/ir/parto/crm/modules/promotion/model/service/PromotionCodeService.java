package ir.parto.crm.modules.promotion.model.service;

import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.repository.PromotionCodeRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromotionCodeService implements ServiceInterface<PromotionCode> {
    private PromotionCodeRepository promotionCodeRepository;

    @Autowired
    public PromotionCodeService(PromotionCodeRepository promotionCodeRepository) {
        this.promotionCodeRepository = promotionCodeRepository;
    }

    @Override
    @Transactional
    public PromotionCode addNewItem(PromotionCode promotionCode) {
        promotionCode.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        promotionCode.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.promotionCodeRepository.save(promotionCode);
    }

    @Override
    @Transactional
    public PromotionCode updateItem(PromotionCode promotionCode) throws InvocationTargetException, IllegalAccessException {
        PromotionCode exist = this.promotionCodeRepository.findByIsDeletedIsNullAndPromotionCodeId(promotionCode.getPromotionCodeId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, promotionCode);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.promotionCodeRepository.save(exist);
    }

    @Override
    @Transactional
    public PromotionCode deleteItem(PromotionCode promotionCode) {
        PromotionCode exist = this.promotionCodeRepository.findByIsDeletedIsNullAndPromotionCodeId(promotionCode.getPromotionCodeId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.promotionCodeRepository.save(exist);
    }

    @Override
    public List<PromotionCode> findAllItem() {
        return this.promotionCodeRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PromotionCode> findAllItem(Pageable pageable) {
        return this.promotionCodeRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PromotionCode> findAllItemWithDeleted(Pageable pageable) {
        return this.promotionCodeRepository.findAll(pageable);
    }

    @Override
    public PromotionCode findOne(PromotionCode promotionCode) {
        return this.promotionCodeRepository.findByIsDeletedIsNullAndPromotionCodeId(promotionCode.getPromotionCodeId());
    }

    @Override
    public PromotionCode findById(Long id) {
        return this.promotionCodeRepository.findByIsDeletedIsNullAndPromotionCodeId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.promotionCodeRepository.existsByIsDeletedIsNullAndPromotionCodeId(id);
    }
}
