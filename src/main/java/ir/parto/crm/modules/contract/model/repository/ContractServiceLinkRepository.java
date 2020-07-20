package ir.parto.crm.modules.contract.model.repository;

import ir.parto.crm.modules.contract.model.entity.ContractServiceLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractServiceLinkRepository extends JpaRepository<ContractServiceLink, Long>, RepositoryInterface<ContractServiceLink> {
    List<ContractServiceLink> findAllByIsDeletedIsNull();

    Page<ContractServiceLink> findAllByIsDeletedIsNull(Pageable pageable);

    ContractServiceLink findByIsDeletedIsNullAndContractServiceLinkId(Long id);

    boolean existsByIsDeletedIsNullAndContractServiceLinkId(Long id);
}
