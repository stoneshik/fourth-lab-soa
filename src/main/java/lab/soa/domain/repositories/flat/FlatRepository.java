package lab.soa.domain.repositories.flat;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.domain.models.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long>,
    JpaSpecificationExecutor<Flat> {
    @Override
    @EntityGraph(attributePaths = {"coordinates", "house"})
    Page<Flat> findAll(Specification<Flat> specification, Pageable pageable);

    @Modifying
    @Transactional
    @Query(
        value =
        """
        DELETE FROM flats f
        WHERE f.id = (
            SELECT f2.id FROM flats f2
            JOIN houses h ON f2.house_id = h.id
            WHERE
                (:houseName IS NOT NULL AND h.name = :houseName) OR
                (:houseYear IS NOT NULL AND h.year = :houseYear) OR
                (:houseNumberOfFlatsOnFloor IS NOT NULL AND h.number_of_flats_on_floor = :houseNumberOfFlatsOnFloor)
            ORDER BY f2.id
            LIMIT 1
        )
        """,
        nativeQuery = true
    )
    int deleteFirstByHouseCriteria(
        @Param("houseName") String houseName,
        @Param("houseYear") Integer houseYear,
        @Param("houseNumberOfFlatsOnFloor") Integer houseNumberOfFlatsOnFloor
    );

    @Override
    @EntityGraph(attributePaths = {"coordinates", "house"})
    Optional<Flat> findById(Long id);

    @Query(value = "SELECT CAST(SUM(height) AS BIGINT) FROM flats", nativeQuery = true)
    Long sumAllHeights();

    @Query(
        value =
        """
        SELECT f.height as height, COUNT(f) as count
        FROM Flat f
        WHERE f.height IS NOT NULL
        GROUP BY f.height
        ORDER BY COUNT(f) DESC
        """
    )
    List<HeightGroupProjection> getHeightDistributionByCount();
}
