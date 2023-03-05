package cinema.controller;

import cinema.domain.cinemaroom.CinemaRoomResponseDTO;
import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.stats.CinemaRoomStatsResponseDTO;
import cinema.domain.ticket.PurchaseResponseDTO;
import cinema.domain.ticket.ReturnedTicketRequestDTO;
import cinema.domain.ticket.ReturnedTicketResponseDTO;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping("/return")
    public ReturnedTicketResponseDTO returnTicket(@RequestBody ReturnedTicketRequestDTO ticket) {
        return cinemaService.returnTicket(ticket);
    }

    @PostMapping("/stats")
    public CinemaRoomStatsResponseDTO getStats(@RequestParam Optional<String> password) {
        return cinemaService.getStats(password);
    }

}
