package cinema.controller;

import cinema.domain.cinemaroom.CinemaRoomResponseDTO;
import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.ticket.PurchaseResponseDTO;
import cinema.domain.ticket.ReturnedTicketRequestDTO;
import cinema.domain.ticket.ReturnedTicketResponseDTO;
import cinema.service.CinemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

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

    @PostMapping("/return")
    public ReturnedTicketResponseDTO returnTicket(@RequestBody ReturnedTicketRequestDTO returnedTicket) {
        return cinemaService.returnTicket(returnedTicket);
    }
}
