package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStateAction;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketStateActionRepository extends JpaRepository<TicketStateAction, Long>, RepositoryInterface<TicketStateAction> {
    TicketStateAction findByIsDeletedIsNullAndTicketStateActionId(Long ticketStateActionId);

    List<TicketStateAction> findAllByIsDeletedIsNull();

    Page<TicketStateAction> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketStateActionId(Long id);

    TicketStateAction findByIsDeletedIsNullAndTitle(String title);
}
