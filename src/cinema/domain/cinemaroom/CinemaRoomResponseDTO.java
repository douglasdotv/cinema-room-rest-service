package cinema.domain.cinemaroom;

import cinema.domain.seat.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CinemaRoomResponseDTO(@JsonProperty("total_rows")
                                    int totalRows,
                                    @JsonProperty("total_columns")
                                    int totalColumns,
                                    @JsonProperty("available_seats")
                                    List<Seat> availableSeats) {

    public CinemaRoomResponseDTO(CinemaRoom cinemaRoom) {
        this(cinemaRoom.getTotalRows(), cinemaRoom.getTotalColumns(), cinemaRoom.getAvailableSeats());
    }

}
