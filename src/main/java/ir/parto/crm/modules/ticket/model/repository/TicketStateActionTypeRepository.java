package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStateActionType;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStateActionTypeRepository extends JpaRepository<TicketStateActionType, Long>, RepositoryInterface<TicketStateActionType> {
}
