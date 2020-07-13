package ir.parto.crm.modules.promotion.model.service;

import ir.parto.crm.modules.promotion.model.entity.PromotionCode;
import ir.parto.crm.modules.promotion.model.entity.PromotionCodeProduct;
import ir.parto.crm.modules.promotion.model.repository.PromotionCodeProductRepository;
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
public class PromotionCodeProductService implements ServiceInterface<PromotionCodeProduct> {
    private PromotionCodeProductRepository promotionCodeProductRepository;

    @Autowired
    public PromotionCodeProductService(PromotionCodeProductRepository promotionCodeProductRepository) {
        this.promotionCodeProductRepository = promotionCodeProductRepository;
    }

    @Override
    @Transactional
    public PromotionCodeProduct addNewItem(PromotionCodeProduct promotionCodeProduct) {
        promotionCodeProduct.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.promotionCodeProductRepository.save(promotionCodeProduct);
    }

    @Override
    @Transactional
    public PromotionCodeProduct updateItem(PromotionCodeProduct promotionCodeProduct) throws InvocationTargetException, IllegalAccessException {
        PromotionCodeProduct exist = this.promotionCodeProductRepository.findByIsDeletedIsNullAndPromotionCodeProductId(promotionCodeProduct.getPromotionCodeProductId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, promotionCodeProduct);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.promotionCodeProductRepository.save(exist);
    }

    @Override
    @Transactional
    public PromotionCodeProduct deleteItem(PromotionCodeProduct promotionCodeProduct) {
        PromotionCodeProduct exist = this.promotionCodeProductRepository.findByIsDeletedIsNullAndPromotionCodeProductId(promotionCodeProduct.getPromotionCodeProductId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.promotionCodeProductRepository.save(exist);
    }

    @Override
    public List<PromotionCodeProduct> findAllItem() {
        return this.promotionCodeProductRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PromotionCodeProduct> findAllItem(Pageable pageable) {
        return this.promotionCodeProductRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PromotionCodeProduct> findAllItemWithDeleted(Pageable pageable) {
        return this.promotionCodeProductRepository.findAll(pageable);
    }

    @Override
    public PromotionCodeProduct findOne(PromotionCodeProduct promotionCodeProduct) {
        return this.promotionCodeProductRepository.findByIsDeletedIsNullAndPromotionCodeProductId(promotionCodeProduct.getPromotionCodeProductId());
    }

    @Override
    public PromotionCodeProduct findById(Long id) {
        return this.promotionCodeProductRepository.findByIsDeletedIsNullAndPromotionCodeProductId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.promotionCodeProductRepository.existsByIsDeletedIsNullAndPromotionCodeProductId(id);
    }

    public Page<PromotionCodeProduct> findAllItemByPromotionCode(PromotionCode promotionCode, Pageable pageable) {
        return this.promotionCodeProductRepository.findAllByIsDeletedIsNullAndPromotionCode(promotionCode, pageable);
    }
}
