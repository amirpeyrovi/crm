package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.Ticket;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, RepositoryInterface<Ticket> {
    Ticket findByIsDeletedIsNullAndTicketId(Long ticketId);

    List<Ticket> findAllByIsDeletedIsNull();

    Page<Ticket> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketId(Long id);

    Ticket findByIsDeletedIsNullAndTitle(String title);
}
