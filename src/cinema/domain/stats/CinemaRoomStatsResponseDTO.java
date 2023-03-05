package cinema.domain.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CinemaRoomStatsResponseDTO(@JsonProperty("current_income")
                                         int currentIncome,
                                         @JsonProperty("number_of_available_seats")
                                         int numberOfAvailableSeats,
                                         @JsonProperty("number_of_purchased_tickets")
                                         int numberOfPurchasedTickets
) {

    public CinemaRoomStatsResponseDTO(CinemaRoomStatsInfo stats) {
        this(
                stats.getCurrentIncome(),
                stats.getNumberOfAvailableSeats(),
                stats.getNumberOfPurchasedTickets()
        );
    }

}
