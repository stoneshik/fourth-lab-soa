package lab.soa.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "flats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Flat {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flat_seq")
    @SequenceGenerator(name = "flat_seq", sequenceName = "flat_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ToString.Include
    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Valid
    @NotNull
    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    @JoinColumn(
        name = "coordinates_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private Coordinates coordinates;

    @ToString.Include
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ToString.Include
    @Positive
    @Column(name = "area", nullable = true)
    private Integer area;

    @ToString.Include
    @NotNull
    @Positive
    @Column(name = "number_of_rooms", nullable = false)
    private Integer numberOfRooms;

    @ToString.Include
    @NotNull
    @Positive
    @Column(name = "height", nullable = false)
    private Integer height;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(name = "view", nullable = true)
    private View view;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(name = "transport", nullable = true)
    private Transport transport;

    @Valid
    @NotNull
    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    @JoinColumn(
        name = "house_id",
        nullable = false,
        foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private House house;

    @ToString.Include
    @NotNull
    @Positive
    @Digits(integer = 12, fraction = 2)
    @Column(
        name = "price",
        nullable = false,
        precision = 14,
        scale = 2
    )
    private BigDecimal price;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(name = "balcony_type", nullable = true)
    private BalconyType balconyType;

    @ToString.Include
    @NotNull
    @Positive
    @Column(name = "walking_minutes_to_metro", nullable = false)
    private Integer walkingMinutesToMetro;

    @ToString.Include
    @NotNull
    @Positive
    @Column(name = "transport_minutes_to_metro", nullable = false)
    private Integer transportMinutesToMetro;

    @PrePersist
    protected void onCreate() {
        if (this.creationDate != null) {
            return;
        }
        this.creationDate = LocalDateTime.now();
    }
}
