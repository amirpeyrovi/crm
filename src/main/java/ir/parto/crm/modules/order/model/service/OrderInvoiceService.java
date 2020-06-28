package ir.parto.crm.modules.order.model.service;

import ir.parto.crm.modules.order.model.entity.OrderInvoice;
import ir.parto.crm.modules.order.model.repository.OrderInvoiceRepository;
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
public class OrderInvoiceService implements ServiceInterface<OrderInvoice> {
    private OrderInvoiceRepository orderInvoiceRepository;

    @Autowired
    public OrderInvoiceService(OrderInvoiceRepository orderInvoiceRepository) {
        this.orderInvoiceRepository = orderInvoiceRepository;
    }

    @Override
    @Transactional
    public OrderInvoice addNewItem(OrderInvoice orderInvoice) {
        return this.orderInvoiceRepository.save(orderInvoice);
    }

    @Override
    @Transactional
    public OrderInvoice updateItem(OrderInvoice orderInvoice) throws InvocationTargetException, IllegalAccessException {
        OrderInvoice exist = this.orderInvoiceRepository.getOne(orderInvoice.getOrderInvoiceId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist,orderInvoice);
        return this.orderInvoiceRepository.save(exist);
    }

    @Override
    @Transactional
    public List<OrderInvoice> deleteItem(OrderInvoice orderInvoice) {
        this.orderInvoiceRepository.delete(orderInvoice);
        return this.orderInvoiceRepository.findAll();
    }

    @Override
    public List<OrderInvoice> findAllItem() {
        return this.orderInvoiceRepository.findAll();
    }

    @Override
    public Page<OrderInvoice> findAllItem(Pageable pageable) {
        return this.orderInvoiceRepository.findAll(pageable);
    }

    @Override
    public OrderInvoice findOne(OrderInvoice orderInvoice) {
        if(this.orderInvoiceRepository.existsById(orderInvoice.getOrderInvoiceId())){
            return this.orderInvoiceRepository.getOne(orderInvoice.getOrderInvoiceId());
        }
        return null;
    }

    @Override
    public OrderInvoice findById(Long id) {
        if(this.orderInvoiceRepository.existsById(id)){
            return this.orderInvoiceRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.orderInvoiceRepository.existsById(id);
    }
}
