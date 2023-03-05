package cinema.service;

import cinema.domain.cinemaroom.CinemaRoom;
import cinema.domain.cinemaroom.CinemaRoomResponseDTO;
import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.seat.Seat;
import cinema.domain.stats.CinemaRoomStatsInfo;
import cinema.domain.stats.CinemaRoomStatsResponseDTO;
import cinema.domain.ticket.PurchaseResponseDTO;
import cinema.domain.ticket.ReturnedTicketRequestDTO;
import cinema.domain.ticket.ReturnedTicketResponseDTO;
import cinema.domain.ticket.Ticket;
import cinema.exception.InvalidTicketException;
import cinema.exception.UnavailableSeatException;
import cinema.exception.WrongPasswordException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final String PASSWORD = "super_secret"; // Just a test password
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

    public ReturnedTicketResponseDTO returnTicket(ReturnedTicketRequestDTO ticket) {
        UUID ticketToken = ticket.token();

        Ticket returnedTicket = cinemaRoom.getPurchasedTickets().stream()
                .filter(t -> t.getToken().equals(ticketToken))
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException("Wrong token!"));

        cinemaRoom.getSeat(returnedTicket.getRow(), returnedTicket.getColumn()).setPurchased(false);
        cinemaRoom.getPurchasedTickets().remove(returnedTicket);

        return new ReturnedTicketResponseDTO(returnedTicket);
    }

    public CinemaRoomStatsResponseDTO getStats(Optional<String> password) {
        validatePassword(password);
        CinemaRoomStatsInfo stats = new CinemaRoomStatsInfo(cinemaRoom);
        return new CinemaRoomStatsResponseDTO(stats);
    }

    private void validateSeat(Seat seat) {
        if (seat == null) {
            throw new UnavailableSeatException("The number of a row or a column is out of bounds!");
        }
        if (seat.isPurchased()) {
            throw new UnavailableSeatException("The ticket has been already purchased!");
        }
    }

    private void validatePassword(Optional<String> password) {
        password.filter(p -> p.equals(PASSWORD))
                .orElseThrow(() -> new WrongPasswordException("The password is wrong!"));
    }

}
