package cinema.controller;

import cinema.domain.cinemaroom.CinemaRoomResponseDTO;
import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.seat.PurchaseResponseDTO;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public CinemaRoomResponseDTO getAvailableSeats() {
        return cinemaService.getAvailableSeats();
    }

    @PostMapping("/purchase")
    public PurchaseResponseDTO purchaseTicket(@RequestBody PurchaseRequestDTO purchaseRequest) {
        return cinemaService.purchaseTicket(purchaseRequest);
    }

}
