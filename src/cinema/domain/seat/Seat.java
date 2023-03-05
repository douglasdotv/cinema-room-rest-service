package cinema.domain.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Synchronized;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean isPurchased;

    public Seat(PurchaseRequestDTO purchaseRequest) {
        // Only row and column are required to retrieve a seat with getSeat() method
        this.row = purchaseRequest.row();
        this.column = purchaseRequest.column();
    }

    @JsonIgnore
    @Synchronized
    public void setPurchased(boolean purchased) {
        this.isPurchased = purchased;
    }

    @Synchronized
    public boolean isPurchased() {
        return isPurchased;
    }

}
