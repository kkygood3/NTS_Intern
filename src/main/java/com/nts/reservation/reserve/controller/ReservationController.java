package com.nts.reservation.reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.display.dto.DisplayResponse;
import com.nts.reservation.display.service.DisplayService;
import com.nts.reservation.product.dto.ProductPrice;

@Controller
public class ReservationController {
	
	// XXX getLogger로 불러오면 싱글톤과 유사한 형식으로 하나의 인스턴스를 사용하는 것?
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private DisplayService displayService;

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd. (E)");
	
	// XXX 0값을 2개 이상 써야될 때도 전부 변수 선언을 하여 사용?
	private static final int NONE_COMMENT = 0;
	private static final int NONE_DISPLAY = 0;

	@GetMapping(path = "/reserve/{displayInfoId}")
	public ModelAndView goReserve(@PathVariable(name = "displayInfoId") int displayInfoId) throws Exception {
		DisplayResponse displayResponse = displayService.getDisplayInfo(displayInfoId, NONE_COMMENT);

		if (displayResponse.getDisplayInfo().getProductId() == NONE_DISPLAY) {
			Exception e = new IllegalArgumentException("There is no displayInfo!!! (displayInfoId)");
			LOGGER.warn("Bad Request! Parameter / Error Message : {} / displayInfoId : {} / {}", e.getMessage(), displayInfoId, e);
			throw e;
		}

		int minPrice = calculateMinPrice(displayResponse.getProductPrices());

		ModelAndView modelAndView = new ModelAndView("reserve");
		modelAndView.addObject("minPrice", minPrice);
		modelAndView.addObject("dates", computeRandomPeriod());
		modelAndView.addObject("displayResponse", displayResponse);
		return modelAndView;
	}

	@GetMapping(path = "/myreservation")
	public String goMyReserve() {
		return "myreservation";
	}

	/**
	 * List<ProductPrice> 중 가장 작은 할인된 가격을 반환
	 * @param prices
	 * @return discounted min Price
	 */
	private int calculateMinPrice(List<ProductPrice> prices) {
		// TODO exception? -> price가 0이면 data 오류이기 때문
		ProductPrice minPrice = prices.stream().reduce((prev, next) -> {
			return (calculateDiscountPrice(prev) < calculateDiscountPrice(next)) ? prev : next;
		}).orElseThrow(IllegalArgumentException::new);

		return calculateDiscountPrice(minPrice);
	}

	private int calculateDiscountPrice(ProductPrice price) {
		return (int)(price.getPrice() * (1 - price.getDiscountRate() / 100));
	}
	
	private Map<String, String> computeRandomPeriod() {
		LocalDate now = LocalDate.now();
		int radomValue = new Random().nextInt(5) + 1;
		
		Map<String, String> dates = new HashMap<>();
		dates.put("now", now.format(DATE_TIME_FORMATTER));
		dates.put("randomDate", now.plusDays(radomValue).format(DATE_TIME_FORMATTER).split(" ")[0]);
		dates.put("maxDate", now.plusDays(5).format(DATE_TIME_FORMATTER));
		return dates;
	}
}
