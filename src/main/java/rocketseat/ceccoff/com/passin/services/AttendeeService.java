package rocketseat.ceccoff.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.ceccoff.com.passin.domain.attendee.Attendee;
import rocketseat.ceccoff.com.passin.domain.checkin.Checkin;
import rocketseat.ceccoff.com.passin.dto.attendee.AttendeeDetails;
import rocketseat.ceccoff.com.passin.dto.attendee.AttendeesListResponseDTO;
import rocketseat.ceccoff.com.passin.repositories.AttendeeRepository;
import rocketseat.ceccoff.com.passin.repositories.CheckInRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());

            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null);

            return new AttendeeDetails(
                    attendee.getId(),
                    attendee.getName(),
                    attendee.getEmail(),
                    attendee.getCreatedAt(),
                    checkedInAt
            );
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}
