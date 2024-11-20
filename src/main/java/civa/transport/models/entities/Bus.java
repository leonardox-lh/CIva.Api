package civa.transport.models.entities;

import civa.transport.models.enums.EBusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Builder
@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long busId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String plate;

    @CreatedDate
    private Date dateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EBusType brand;

    @Column(nullable = false)
    private Boolean available;


    public Bus(String name, String plate, EBusType brand, Boolean available) {
        this.name = name;
        this.plate = plate;
        this.brand = brand;
        this.available = available;
    }

}
