package acs.rest;

import acs.boundary.TicketBoundary;
import acs.logic.TicketService;
import acs.logic.utils.TicketFilterType;
import acs.utils.TicketSortBy;
import acs.utils.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

//    //GET
// Get all tickets by filter type with pagination
    @RequestMapping(path = "/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketBoundary[] getAllTickets(
            @RequestParam(name = "filterType", required = false) TicketFilterType ticketFilterType,
            @RequestParam(name = "filterValue", required = false) String filterValue,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "ticketSortBy", required = false, defaultValue = "EMAIL") TicketSortBy ticketSortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") SortOrder sortOrder) {
        return ticketService.getAllTickets(ticketFilterType, filterValue, size, page, ticketSortBy, sortOrder).
                toArray(new TicketBoundary[0]);
    }

    //POST
    // Create ticket
    @RequestMapping(path = "/ticket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketBoundary createTicket(@RequestBody TicketBoundary ticketBoundary) {
        return this.ticketService.createTicket(ticketBoundary);
    }

    //PUT
    // Update user details
    @RequestMapping(path = "/ticket", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void closeTicket(@RequestBody TicketBoundary update) {
        this.ticketService.closeTicket(update);
    }

}
