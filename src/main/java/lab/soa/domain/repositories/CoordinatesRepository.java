package lab.soa.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.soa.domain.models.Coordinates;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}
