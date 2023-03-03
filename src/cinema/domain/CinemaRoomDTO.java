package cinema.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CinemaRoomDTO(@JsonProperty("total_rows")
                            int totalRows,
                            @JsonProperty("total_columns")
                            int totalColumns,
                            @JsonProperty("available_seats")
                            List<Seat> availableSeats) {

    public CinemaRoomDTO(CinemaRoom cinemaRoom) {
        this(cinemaRoom.getTotalRows(), cinemaRoom.getTotalColumns(), cinemaRoom.getAvailableSeats());
    }

}
