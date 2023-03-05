package cinema.controller;

import cinema.domain.seat.PurchaseRequestDTO;
import cinema.domain.ticket.ReturnedTicketRequestDTO;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAvailableSeats() {
        return ResponseEntity.ok(cinemaService.getAvailableSeats());
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody PurchaseRequestDTO purchaseRequest) {
        return ResponseEntity.ok(cinemaService.purchaseTicket(purchaseRequest));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody ReturnedTicketRequestDTO ticket) {
        return ResponseEntity.ok(cinemaService.returnTicket(ticket));
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam Optional<String> password) {
        return ResponseEntity.ok(cinemaService.getStats(password));
    }

}
