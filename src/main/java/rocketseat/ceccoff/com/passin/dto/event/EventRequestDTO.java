package rocketseat.ceccoff.com.passin.dto.event;

public record EventRequestDTO(
        String title,
        String details,
        Integer maximumAttendees
) {
}
