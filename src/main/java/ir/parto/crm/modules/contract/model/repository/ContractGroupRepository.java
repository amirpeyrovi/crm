package ir.parto.crm.modules.contract.model.repository;

import ir.parto.crm.modules.contract.model.entity.ContractGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractGroupRepository extends JpaRepository<ContractGroup, Long>, RepositoryInterface<ContractGroup> {
    List<ContractGroup> findAllByIsDeletedIsNull();

    Page<ContractGroup> findAllByIsDeletedIsNull(Pageable pageable);

    ContractGroup findByIsDeletedIsNullAndContractGroupId(Long id);

    boolean existsByIsDeletedIsNullAndContractGroupId(Long id);
}
