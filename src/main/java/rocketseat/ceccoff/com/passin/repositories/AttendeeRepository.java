package rocketseat.ceccoff.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.ceccoff.com.passin.domain.attendee.Attendee;
import rocketseat.ceccoff.com.passin.domain.event.Event;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
