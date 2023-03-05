package cinema.domain.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReturnedTicketResponseDTO(@JsonProperty("returned_ticket")
                                        TicketResponseDTO ticket) {

    public ReturnedTicketResponseDTO(Ticket ticket) {
        this(new TicketResponseDTO(ticket.getRow(), ticket.getColumn(), ticket.getPrice()));
    }

}
