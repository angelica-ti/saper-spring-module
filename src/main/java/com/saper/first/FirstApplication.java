package com.saper.first;

import com.saper.first.dtos.CostResponseDTO;
import com.saper.first.dtos.PriceResponseDTO;
import com.saper.first.services.PayService;
import com.saper.first.services.TimeService;
import com.saper.first.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class FirstApplication implements CommandLineRunner {

//	@Autowired
	TimeService timeService;
	TypeService typeService;
	PayService payService;

	public FirstApplication(TimeService timeService, TypeService typeService, PayService payService) {
		this.timeService = timeService;
		this.typeService = typeService;
		this.payService = payService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/test")
	public String test(@RequestParam(value = "first", defaultValue = "Angélica") String first,
					   @RequestParam(value = "second", defaultValue = "Viana") String second) {
		return String.format("Hello, %s %s!", first, second);
	}

	@GetMapping("/calc-cost")
	public CostResponseDTO calcCost(@RequestParam(value = "minutes", defaultValue = "0") int minutes,
									@RequestParam(value = "type", defaultValue = "carro") String type) {
		return new CostResponseDTO(payService.calcCost(minutes, type));
	}

	@GetMapping("/price")
	public PriceResponseDTO price() {
//		HashMap<String, Object> ans = new HashMap<>();
//		ans.put("moto", typeService.calcType("moto"));
//		ans.put("carro", typeService.calcType("carro"));
		return new PriceResponseDTO(typeService.calcType("moto"),
									typeService.calcType("carro"));
	}

	@Override
	public void run(String... args) throws Exception {
		TimeService timeService = new TimeService();
		TypeService typeService = new TypeService();
		PayService payService = new PayService(typeService, timeService);

		System.out.println(payService.calcCost(78, "moto"));
	}
}
