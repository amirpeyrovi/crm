package ir.parto.crm.modules.contract.model.repository;

import ir.parto.crm.modules.contract.model.entity.ContractProductGroupLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractProductGroupLinkRepository extends JpaRepository<ContractProductGroupLink, Long>, RepositoryInterface<ContractProductGroupLink> {
    List<ContractProductGroupLink> findAllByIsDeletedIsNull();

    Page<ContractProductGroupLink> findAllByIsDeletedIsNull(Pageable pageable);

    ContractProductGroupLink findByIsDeletedIsNullAndContractProductGroupLinkId(Long id);

    boolean existsByIsDeletedIsNullAndContractProductGroupLinkId(Long id);
}
