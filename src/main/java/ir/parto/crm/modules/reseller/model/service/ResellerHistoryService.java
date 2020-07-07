package ir.parto.crm.modules.reseller.model.service;

import ir.parto.crm.modules.reseller.model.entity.ResellerHistory;
import ir.parto.crm.modules.reseller.model.repository.ResellerHistoryRepository;
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
public class ResellerHistoryService implements ServiceInterface<ResellerHistory> {
    private ResellerHistoryRepository resellerHistoryRepository;

    @Autowired
    public ResellerHistoryService(ResellerHistoryRepository resellerHistoryRepository) {
        this.resellerHistoryRepository = resellerHistoryRepository;
    }

    @Override
    @Transactional
    public ResellerHistory addNewItem(ResellerHistory resellerHistory) {
        resellerHistory.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.resellerHistoryRepository.save(resellerHistory);
    }

    @Override
    @Transactional
    public ResellerHistory updateItem(ResellerHistory resellerHistory) throws InvocationTargetException, IllegalAccessException {
        ResellerHistory exist = this.resellerHistoryRepository.findByIsDeletedIsNullAndResellerHistoryId(resellerHistory.getResellerHistoryId());
        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(exist, resellerHistory);
        exist.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.resellerHistoryRepository.save(exist);
    }

    @Override
    @Transactional
    public ResellerHistory deleteItem(ResellerHistory resellerHistory) {
        ResellerHistory exist = this.resellerHistoryRepository.findByIsDeletedIsNullAndResellerHistoryId(resellerHistory.getResellerHistoryId());
        exist.setIsDeleted(1);
        exist.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        exist.setDeletedDate(LocalDateTime.now());
        return this.resellerHistoryRepository.save(exist);
    }

    @Override
    public List<ResellerHistory> findAllItem() {
        return this.resellerHistoryRepository.findAllByIsDeletedIsNull();
    }

    @Override
    public Page<ResellerHistory> findAllItem(Pageable pageable) {
        return this.resellerHistoryRepository.findAllByIsDeletedIsNull(pageable);
    }

    @Override
    public Page<ResellerHistory> findAllItemWithDeleted(Pageable pageable) {
        return this.resellerHistoryRepository.findAll(pageable);
    }

    @Override
    public ResellerHistory findOne(ResellerHistory resellerHistory) {
        return this.resellerHistoryRepository.findByIsDeletedIsNullAndResellerHistoryId(resellerHistory.getResellerHistoryId());
    }

    @Override
    public ResellerHistory findById(Long id) {
        return this.resellerHistoryRepository.findByIsDeletedIsNullAndResellerHistoryId(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.resellerHistoryRepository.existsByIsDeletedIsNullAndResellerHistoryId(id);
    }
}
