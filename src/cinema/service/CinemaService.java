package cinema.service;

import cinema.domain.cinemaroom.CinemaRoom;
import cinema.domain.cinemaroom.CinemaRoomResponseDTO;
import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.seat.PurchaseResponseDTO;
import cinema.domain.seat.Seat;
import cinema.exception.UnavailableSeatException;
import org.springframework.stereotype.Service;

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
        return new PurchaseResponseDTO(cinemaRoomSeat);
    }

    private void validateSeat(Seat seat) {
        if (seat == null) {
            throw new UnavailableSeatException("The number of a row or a column is out of bounds!");
        }

        if (seat.isPurchased()) {
            throw new UnavailableSeatException("The ticket has been already purchased!");
        }
    }

}
