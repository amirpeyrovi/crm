package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketNote;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketNoteRepository extends JpaRepository<TicketNote, Long>, RepositoryInterface<TicketNote> {
    TicketNote findByIsDeletedIsNullAndTicketNoteId(Long ticketNoteId);

    List<TicketNote> findAllByIsDeletedIsNull();

    Page<TicketNote> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketNoteId(Long id);
}
