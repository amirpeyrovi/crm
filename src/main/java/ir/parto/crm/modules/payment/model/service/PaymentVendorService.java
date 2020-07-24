package ir.parto.crm.modules.payment.model.service;

import ir.parto.crm.modules.payment.model.entity.PaymentVendor;
import ir.parto.crm.modules.payment.model.repository.PaymentVendorRepository;
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
public class PaymentVendorService implements ServiceInterface<PaymentVendor> {
    private PaymentVendorRepository paymentVendorRepository;
    @Autowired
    public PaymentVendorService(PaymentVendorRepository paymentVendorRepository) {
        this.paymentVendorRepository = paymentVendorRepository;
    }

    @Override
    @Transactional
    public PaymentVendor addNewItem(PaymentVendor paymentVendor) {
        paymentVendor.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.paymentVendorRepository.save(paymentVendor);
    }

    @Override
    @Transactional
    public PaymentVendor updateItem(PaymentVendor paymentVendor) throws InvocationTargetException, IllegalAccessException {
        PaymentVendor exist = this.paymentVendorRepository.findByIsDeletedIsNullAndVendorId(paymentVendor.getVendorId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, paymentVendor);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.paymentVendorRepository.save(exist);
    }

    @Override
    @Transactional
    public PaymentVendor deleteItem(PaymentVendor paymentVendor) {
        PaymentVendor exist = this.paymentVendorRepository.findByIsDeletedIsNullAndVendorId(paymentVendor.getVendorId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.paymentVendorRepository.save(exist);
    }

    @Override
    public List<PaymentVendor> findAllItem() {
        return this.paymentVendorRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<PaymentVendor> findAllItem(Pageable pageable) {
        return this.paymentVendorRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<PaymentVendor> findAllItemWithDeleted(Pageable pageable) {
        return this.paymentVendorRepository.findAll(pageable);
    }

    @Override
    public PaymentVendor findOne(PaymentVendor paymentVendor) {
        return this.paymentVendorRepository.findByIsDeletedIsNullAndVendorId(paymentVendor.getVendorId());
    }

    @Override
    public PaymentVendor findById(Long id) {
        return this.paymentVendorRepository.findByIsDeletedIsNullAndVendorId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.paymentVendorRepository.existsByIsDeletedIsNullAndVendorId(id);
    }
}
