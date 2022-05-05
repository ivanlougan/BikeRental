package pl.joboboss.cyycycu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.joboboss.cyycycu.cyc.BikeService;
import pl.joboboss.cyycycu.cyc.BikeDto;

import java.util.List;

@SpringBootApplication
public class CyycycuApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CyycycuApplication.class, args);


		BikeDto bike1 = new BikeDto(1L, "Kross Esker 4.0, 29 inch", "KRS12345", 30, 100);
		BikeDto bike2 = new BikeDto(2L, "Merida Kalahari 2020, 29 inch", "MK2020X9876", 25, 80);
		BikeDto bike3 = new BikeDto(3L, "Trek XSpeed 2, 29 inch", "TXS229M1234", 30, 120);
		BikeDto bike4 = new BikeDto(4L, "Superior XR Super, 26 inch", "SXRS26ASDF", 40, 150);
		BikeService bean = context.getBean(BikeService.class);
		bean.add(bike1);
		bean.add(bike2);
		bean.add(bike3);
		bean.add(bike4);

		double payment = bean.rentForDay("TXS229M1234", "chuuuukij34234");
		System.out.println("Bike was rented for: " + payment);

		int borrowedBikes = bean.countBorrowedBikes();
		System.out.println("Number of rented bikes: " + borrowedBikes);

		System.out.println("Available bikes (price ascending): ");
		bean.findAllAvailableBikes().forEach(System.out::println);









//		Bike bike2 = new Bike(2L, "Trek", "Truper", 50, 200);
//		bean.save(bike2);
//		System.out.println("Bike2 has been saved to the database");
//
//		System.out.println("Read bike from database: ");
//		bean.findById(1L).ifPresent(System.out::println);
//
//		bean.deleteById(1L);
//		System.out.println("bike has been deleted from db");
//
//		System.out.println("Read bike from database: ");
//		bean.findById(1L).ifPresentOrElse(System.out::println, () -> System.out.println("There's no such record in db! "));

//		bean.findById(1L).ifPresent(System.out::println);
	}

}
