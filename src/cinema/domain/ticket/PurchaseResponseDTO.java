package cinema.domain.ticket;

import java.util.UUID;

public record PurchaseResponseDTO(UUID token, TicketResponseDTO ticket) {

    public PurchaseResponseDTO(Ticket ticket) {
        this(ticket.getToken(), new TicketResponseDTO(
                ticket.getRow(), ticket.getColumn(), ticket.getPrice()));
    }

}
