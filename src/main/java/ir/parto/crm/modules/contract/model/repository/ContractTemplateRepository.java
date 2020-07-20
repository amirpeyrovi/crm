package ir.parto.crm.modules.contract.model.repository;

import ir.parto.crm.modules.contract.model.entity.ContractTemplate;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractTemplateRepository extends JpaRepository<ContractTemplate, Long>, RepositoryInterface<ContractTemplate> {
    List<ContractTemplate> findAllByIsDeletedIsNull();

    Page<ContractTemplate> findAllByIsDeletedIsNull(Pageable pageable);

    ContractTemplate findByIsDeletedIsNullAndContractTemplateId(Long id);

    boolean existsByIsDeletedIsNullAndContractTemplateId(Long id);
}
