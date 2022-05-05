package pl.joboboss.cyycycu.cyc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

interface BikeRepository extends CrudRepository<Bike, Long> {
    Optional<Bike> findBySerialNoIgnoreCase(String serialNo);
    int countAllByBorrowerIdIsNotNull();
    List<Bike> findAllByBorrowerIdIsNullOrderByDayPrice();














//
//    private EntityManager entityManager;
//
//    public BikeRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public void save(Bike bike) {
//            entityManager.persist(bike);
//    }
//
//    public Optional<Bike> findById(Long id) {
//        return Optional.ofNullable(entityManager.find(Bike.class, id));
//    }
//
//
//    public void deleteById(Long id) {
//        findById(id).ifPresent(entityManager::remove);
//        Bike bike = entityManager.find(Bike.class, id);
//        if (bike != null) {
//            entityManager.remove(bike);
//        }


}
