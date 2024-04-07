package rocketseat.ceccoff.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.ceccoff.com.passin.domain.attendee.Attendee;
import rocketseat.ceccoff.com.passin.repositories.AttendeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private AttendeeRepository attendeeRepository;
    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }
}
