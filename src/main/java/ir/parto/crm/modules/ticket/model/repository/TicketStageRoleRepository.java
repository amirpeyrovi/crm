package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStageRole;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketStageRoleRepository extends JpaRepository<TicketStageRole, Long>, RepositoryInterface<TicketStageRole> {

    TicketStageRole findByIsDeletedIsNullAndTicketStageRoleId(Long ticketStageRoleId);

    List<TicketStageRole> findAllByIsDeletedIsNull();

    Page<TicketStageRole> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketStageRoleId(Long id);
}
