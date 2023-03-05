package cinema.domain.stats;

import cinema.domain.cinemaroom.CinemaRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaRoomStatsInfo {

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public CinemaRoomStatsInfo(CinemaRoom cinemaRoom) {
        this.currentIncome = cinemaRoom.getCurrentIncome();
        this.numberOfAvailableSeats = cinemaRoom.getNumberOfAvailableSeats();
        this.numberOfPurchasedTickets = cinemaRoom.getNumberOfPurchasedTickets();
    }

}
