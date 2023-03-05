package cinema.service;

import cinema.domain.cinemaroom.CinemaRoom;
import cinema.domain.cinemaroom.CinemaRoomResponseDTO;
import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.ticket.PurchaseResponseDTO;
import cinema.domain.seat.Seat;
import cinema.domain.ticket.ReturnedTicketRequestDTO;
import cinema.domain.ticket.Ticket;
import cinema.domain.ticket.ReturnedTicketResponseDTO;
import cinema.exception.InvalidTicketException;
import cinema.exception.UnavailableSeatException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private final CinemaRoom cinemaRoom = new CinemaRoom(ROWS, COLUMNS);

    public CinemaRoomResponseDTO getAvailableSeats() {
        return new CinemaRoomResponseDTO(cinemaRoom);
    }

    public PurchaseResponseDTO purchaseTicket(PurchaseRequestDTO purchaseRequest) {
        Seat requestedSeat = new Seat(purchaseRequest);
        Seat cinemaRoomSeat = cinemaRoom.getSeat(requestedSeat.getRow(), requestedSeat.getColumn());

        validateSeat(cinemaRoomSeat);
        cinemaRoomSeat.setPurchased(true);

        Ticket ticket = new Ticket(cinemaRoomSeat);
        cinemaRoom.getPurchasedTickets().add(ticket);
        return new PurchaseResponseDTO(ticket);
    }

    private void validateSeat(Seat seat) {
        if (seat == null) {
            throw new UnavailableSeatException("The number of a row or a column is out of bounds!");
        }

        if (seat.isPurchased()) {
            throw new UnavailableSeatException("The ticket has been already purchased!");
        }
    }

    public ReturnedTicketResponseDTO returnTicket(ReturnedTicketRequestDTO returnedTicket) {
        UUID ticketToken = returnedTicket.token();

        Ticket ticket = cinemaRoom.getPurchasedTickets().stream()
                .filter(t -> t.getToken().equals(ticketToken))
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException("Wrong token!"));

        Seat seat = cinemaRoom.getSeat(ticket.getRow(), ticket.getColumn());
        seat.setPurchased(false);

        return new ReturnedTicketResponseDTO(ticket);
    }

}
