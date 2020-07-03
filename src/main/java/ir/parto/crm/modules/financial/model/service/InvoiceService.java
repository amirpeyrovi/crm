package ir.parto.crm.modules.financial.model.service;

import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.financial.model.entity.Invoice;
import ir.parto.crm.modules.financial.model.repository.InvoiceRepository;
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
public class InvoiceService implements ServiceInterface<Invoice> {
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    @Transactional
    public Invoice addNewItem(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    @Transactional
    public Invoice updateItem(Invoice invoice) throws InvocationTargetException, IllegalAccessException {
        Invoice exist = this.invoiceRepository.getOne(invoice.getInvoiceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, invoice);
        return this.invoiceRepository.save(exist);
    }

    @Override
    @Transactional
    public Invoice deleteItem(Invoice invoice) {
        Admin authentication = (Admin) SecurityContextHolder.getContext().getAuthentication().getDetails();
        invoice.setIsDeleted(1);
        invoice.setDeletedAt(LocalDateTime.now());
        invoice.setDeletedBy(authentication.getUsername());
        return this.invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAllItem() {
        return this.invoiceRepository.findAll();
    }

    @Override
    public Page<Invoice> findAllItem(Pageable pageable) {
        return this.invoiceRepository.findAll(pageable);
    }

    @Override
    public Page<Invoice> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Invoice findOne(Invoice invoice) {
        return this.invoiceRepository.getOne(invoice.getInvoiceId());
    }

    @Override
    public Invoice findById(Long id) {
        if(this.invoiceRepository.existsById(id)){
            return this.invoiceRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.invoiceRepository.existsById(id);
    }
}
