package rocketseat.ceccoff.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.ceccoff.com.passin.domain.checkin.Checkin;

import java.util.List;
import java.util.Optional;

public interface CheckInRepository extends JpaRepository<Checkin, Integer> {

    Optional<Checkin> findByAttendeeId(String attendeeId);
}
