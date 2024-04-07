package rocketseat.ceccoff.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.ceccoff.com.passin.domain.attendee.Attendee;
import rocketseat.ceccoff.com.passin.domain.event.Event;
import rocketseat.ceccoff.com.passin.dto.event.EventIdDTO;
import rocketseat.ceccoff.com.passin.dto.event.EventRequestDTO;
import rocketseat.ceccoff.com.passin.dto.event.EventResponseDTO;
import rocketseat.ceccoff.com.passin.repositories.AttendeeRepository;
import rocketseat.ceccoff.com.passin.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(
                () -> new RuntimeException("Event not found with ID:" + eventId)
        );

        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);

        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventRequestDTO) {
        Event newEvent = new Event();

        newEvent.setTitle(eventRequestDTO.title());
        newEvent.setDetails(eventRequestDTO.details());
        newEvent.setMaximumAttendees(eventRequestDTO.maximumAttendees());

        String slug = this.createSlug(eventRequestDTO.title());

        newEvent.setSlug(slug);

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);

        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
