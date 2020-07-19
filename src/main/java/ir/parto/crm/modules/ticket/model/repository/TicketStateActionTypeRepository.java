package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketStateActionTypeRepository extends JpaRepository<TicketStateActionType, Long>, RepositoryInterface<TicketStateActionType> {
    TicketStateActionType findByIsDeletedIsNullAndTicketStateActionTypeId(Long ticketStateActionTypeId);

    List<TicketStateActionType> findAllByIsDeletedIsNull();

    Page<TicketStateActionType> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketStateActionTypeId(Long id);

    TicketStateActionType findByIsDeletedIsNullAndTitle(String title);

    TicketStateActionType findByIsDeletedIsNullAndTicketId(Long ticketStateActionTypeId);

    Boolean existsByIsDeletedIsNullAndTicketId(Long id);
}
