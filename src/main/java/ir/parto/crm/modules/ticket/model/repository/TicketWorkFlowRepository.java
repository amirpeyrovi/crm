package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketWorkFlow;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketWorkFlowRepository extends JpaRepository<TicketWorkFlow, Long>, RepositoryInterface<TicketWorkFlow> {
    TicketWorkFlow findByIsDeletedIsNullAndTicketWorkFlowId(Long ticketWorkFlowId);

    List<TicketWorkFlow> findAllByIsDeletedIsNull();

    Page<TicketWorkFlow> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketWorkFlowId(Long id);

    TicketWorkFlow findByIsDeletedIsNullAndTitle(String title);
}
