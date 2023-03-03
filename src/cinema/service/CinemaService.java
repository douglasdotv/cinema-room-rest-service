package cinema.service;

import cinema.domain.CinemaRoom;
import cinema.domain.CinemaRoomDTO;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    public CinemaRoomDTO getAvailableSeats() {
        CinemaRoom cinemaRoom = new CinemaRoom(ROWS, COLUMNS);
        return new CinemaRoomDTO(cinemaRoom);
    }

}
