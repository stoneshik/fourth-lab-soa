package lab.soa.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.soa.domain.models.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
