package rocketseat.ceccoff.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.ceccoff.com.passin.domain.checkin.Checkin;

public interface CheckInRepository extends JpaRepository<Checkin, Integer> {
}
