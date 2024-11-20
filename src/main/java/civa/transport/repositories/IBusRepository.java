package civa.transport.repositories;

import civa.transport.models.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBusRepository extends JpaRepository<Bus, Long> {

}
