package cinema.domain.cinemaroom;

import cinema.domain.seat.Seat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@NoArgsConstructor
public class CinemaRoom {

    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats = new ArrayList<>();

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        IntStream.rangeClosed(1, totalRows)
                .forEach(row -> IntStream.rangeClosed(1, totalColumns)
                        .forEach(column -> availableSeats.add(new Seat(
                                row,
                                column,
                                row <= 4 ? 10 : 8,
                                false)
                        )));
    }

    public Seat getSeat(int row, int column) {
        return availableSeats.stream()
                .filter(s -> s.getRow() == row && s.getColumn() == column)
                .findFirst()
                .orElse(null);
    }

}
