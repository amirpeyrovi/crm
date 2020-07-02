package ir.parto.crm.modules.financial.model.service;

import ir.parto.crm.modules.financial.model.entity.Transaction;
import ir.parto.crm.modules.financial.model.repository.TransactionRepository;
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
public class TransactionService implements ServiceInterface<Transaction> {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction addNewItem(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction updateItem(Transaction transaction) throws InvocationTargetException, IllegalAccessException {
        Transaction exist = this.transactionRepository.getOne(transaction.getTransactionId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, transaction);
        return this.transactionRepository.save(exist);
    }

    @Override
    @Transactional
    public Transaction deleteItem(Transaction transaction) {
        this.transactionRepository.delete(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findAllItem() {
        return this.transactionRepository.findAll();
    }

    @Override
    public Page<Transaction> findAllItem(Pageable pageable) {
        return this.transactionRepository.findAll(pageable);
    }

    @Override
    public Page<Transaction> findAllItemWithDeleted(Pageable pageable) {
        return null;
    }

    @Override
    public Transaction findOne(Transaction transaction) {
        return this.transactionRepository.getOne(transaction.getTransactionId());
    }

    @Override
    public Transaction findById(Long id) {
        if(this.transactionRepository.existsById(id)){
            return this.transactionRepository.getOne(id);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.transactionRepository.existsById(id);
    }
}
