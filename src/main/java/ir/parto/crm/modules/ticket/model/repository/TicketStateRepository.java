package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketState;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketStateRepository extends JpaRepository<TicketState, Long>, RepositoryInterface<TicketState> {
    TicketState findByIsDeletedIsNullAndTicketStateId(Long ticketStateId);

    List<TicketState> findAllByIsDeletedIsNull();

    Page<TicketState> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketStateId(Long id);

    TicketState findByIsDeletedIsNullAndTitle(String title);
}
