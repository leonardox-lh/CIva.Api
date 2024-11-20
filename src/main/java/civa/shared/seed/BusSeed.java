package civa.shared.seed;

import civa.transport.models.entities.Bus;
import civa.transport.models.enums.EBusType;
import civa.transport.repositories.IBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusSeed implements CommandLineRunner {

    @Autowired
    private IBusRepository busRepository;

    private void seedBusData() {
        var busData = busRepository.findAll();

        if (!busData.isEmpty()) {
            return;
        }

        List<Bus> banks = List.of(
            new Bus(
                "Bus Civa 1",
                "AEF-717",
                EBusType.SCANIA,
                true
            ),
            new Bus(
                "Bus Civa 2",
                "AEF-718",
                EBusType.FIAT,
                false
            ),
            new Bus(
                "Bus Civa 3",
                "AEF-719",
                EBusType.FIAT,
                true
            ),
            new Bus(
                "Bus Civa 4",
                "AEF-720",
                EBusType.FIAT,
                false
            ),
            new Bus(
                "Bus Civa 5",
                "AEF-721",
                EBusType.FIAT,
                true
            ),
            new Bus(
                "Bus Civa 6",
                "AEF-722",
                EBusType.FIAT,
                true
            )
        );

        busRepository.saveAll(banks);
    }

    @Override
    public void run(String... args) {
        seedBusData();
    }
}
