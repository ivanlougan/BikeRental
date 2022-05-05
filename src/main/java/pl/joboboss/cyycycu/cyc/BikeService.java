package pl.joboboss.cyycycu.cyc;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BikeService {
    private BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Transactional
    public void add(BikeDto newBike) {
        Bike bike = new Bike(newBike.getId(),
                newBike.getModel(),
                newBike.getSerialNo(),
                newBike.getHourPrice(),
                newBike.getDayPrice());
        bikeRepository.save(bike);
    }

    @Transactional
    public void deleteById(Long bikeId) {
        bikeRepository.deleteById(bikeId);
    }

    @Transactional
    public double rentForHours(String serialNo, int hours, String borrowerId) {
        LocalDateTime dateOfReturn = LocalDateTime.now().plusHours(hours);
        Bike bike = updateBike(serialNo, borrowerId, dateOfReturn);
        return bike.getHourPrice() * hours;
    }

    @Transactional
    public double rentForDay(String serialNo, String borrowerId) {
        LocalDateTime dateOfReturn = LocalDateTime.now().withHour(23).withMinute(59);
        Bike bike = updateBike(serialNo, borrowerId, dateOfReturn);
        return bike.getDayPrice();
    }

    @Transactional
    public void returnBike(String serialNo) {
        updateBike(serialNo, null, null);
    }

    private Bike updateBike(String serialNo, String borrowerId, LocalDateTime dateOfReturn) {
        Bike bike = bikeRepository.findBySerialNoIgnoreCase(serialNo)
                .orElseThrow(BikeNotFoundException::new);
        bike.setLocalDateTime(dateOfReturn);
        bike.setBorrowerId(borrowerId);
        bikeRepository.save(bike);
        return bike;
    }

    public int countBorrowedBikes() {
        return bikeRepository.countAllByBorrowerIdIsNotNull();
    }

    public List<BikeDto> findAllAvailableBikes() {
        return bikeRepository.findAllByBorrowerIdIsNullOrderByDayPrice()
                .stream().map(bike -> new BikeDto(
                        bike.getId(),
                        bike.getModel(),
                        bike.getSerialNo(),
                        bike.getHourPrice(),
                        bike.getDayPrice()
                )).collect(Collectors.toList());
    }

}
