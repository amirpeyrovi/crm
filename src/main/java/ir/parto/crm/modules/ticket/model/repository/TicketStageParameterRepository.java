package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameter;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStageParameterRepository extends JpaRepository<TicketStageParameter, Long>, RepositoryInterface<TicketStageParameter> {
}
