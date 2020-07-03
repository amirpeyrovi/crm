package ir.parto.crm.modules.product.model.service;

import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;
import ir.parto.crm.modules.product.model.repository.ProductAddonsLinkRepository;
import ir.parto.crm.utils.MyBeanCopy;
import ir.parto.crm.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ProductAddonsLinkService implements ServiceInterface<ProductAddonsLink> {
    private ProductAddonsLinkRepository productAddonsLinkRepository;

    @Autowired
    public ProductAddonsLinkService(ProductAddonsLinkRepository productAddonsLinkRepository) {
        this.productAddonsLinkRepository = productAddonsLinkRepository;
    }

    @Override
    @Transactional
    public ProductAddonsLink addNewItem(ProductAddonsLink productAddonsLink) {
        return this.productAddonsLinkRepository.save(productAddonsLink);
    }

    @Override
    @Transactional
    public ProductAddonsLink updateItem(ProductAddonsLink productAddonsLink) throws InvocationTargetException, IllegalAccessException {
        ProductAddonsLink exist = this.productAddonsLinkRepository.getOne(productAddonsLink.getProductAddonLinkId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, productAddonsLink);
        return this.productAddonsLinkRepository.save(exist);
    }

    @Override
    @Transactional
    public ProductAddonsLink deleteItem(ProductAddonsLink productAddonsLink) {
        this.productAddonsLinkRepository.delete(productAddonsLink);
        return productAddonsLink;
    }

    @Override
    public List<ProductAddonsLink> findAllItem() {
        return this.productAddonsLinkRepository.findAll();
    }

    @Override
    public Page<ProductAddonsLink> findAllItem(Pageable pageable) {
        return this.productAddonsLinkRepository.findAll(pageable);
    }

    @Override
    public Page<ProductAddonsLink> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public ProductAddonsLink findOne(ProductAddonsLink productAddonsLink) {
        return this.productAddonsLinkRepository.getOne(productAddonsLink.getProductAddonLinkId());
    }

    @Override
    public ProductAddonsLink findById(Long id) {
        if(this.productAddonsLinkRepository.existsById(id)){
            return this.productAddonsLinkRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.productAddonsLinkRepository.existsById(id);
    }
}
