package cinema.domain.seat;

public record PurchaseResponseDTO(int row, int column, int price) {

    public PurchaseResponseDTO(Seat seat) {
        this(seat.getRow(), seat.getColumn(), seat.getPrice());
    }

}
