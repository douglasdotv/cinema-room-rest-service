package cinema.domain;

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
                        .forEach(column -> availableSeats.add(new Seat(row, column))));
        }

}
