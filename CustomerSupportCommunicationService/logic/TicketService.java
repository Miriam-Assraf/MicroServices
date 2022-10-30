package acs.logic;

import acs.boundary.TicketBoundary;
import acs.logic.utils.TicketFilterType;
import acs.utils.SortOrder;
import acs.utils.TicketSortBy;
import java.util.List;

public interface TicketService {
    TicketBoundary createTicket(TicketBoundary ticketBoundary);

    void closeTicket(TicketBoundary update);

    List<TicketBoundary> getAllTickets(TicketFilterType ticketFilterType, String filterValue, int size, int page, TicketSortBy ticketSortBy, SortOrder sortOrder);
}
