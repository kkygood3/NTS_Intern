package com.nts.reservation.reserve.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.service.ReservationService;

@Controller
public class ReservationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private DisplayService displayService;
	@Autowired
	private ReservationService reservationService;
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd. (E)");

	// XXX 0값을 2개 이상 써야될 때도 전부 변수 선언을 하여 사용?
	private static final int NONE = 0;

	/**
	 * Display 별 예약화면으로 이동
	 */
	@GetMapping(path = "/reserve/{displayInfoId}")
	public ModelAndView goReserve(@PathVariable(name = "displayInfoId") int displayInfoId, ModelAndView modelAndView) {

		DisplayResponse displayResponse = displayService.getDisplayInfo(displayInfoId, NONE);
		if (displayResponse.getDisplayInfo().getProductId() == NONE) {
			IllegalArgumentException e = new IllegalArgumentException("There is no displayInfo!!! (displayInfoId)");
			LOGGER.warn("Bad Request! Parameter / displayInfoId : {}", displayInfoId, e);
			throw e;
		}

		int minPrice = calculateMinPrice(displayResponse.getProductPrices());

		modelAndView.setViewName("reserve");
		modelAndView.addObject("minPrice", minPrice);
		modelAndView.addObject("dates", computeRandomPeriod());
		modelAndView.addObject("displayResponse", displayResponse);
		return modelAndView;
	}

	/**
	 * session에 email이 저장되어 있다면, email로 저장되어 있는 {@code List<Reservation>}를 반환
	 */
	@GetMapping(path = "/myreservation")
	public ModelAndView goMyReserve(HttpSession session, ModelAndView modelAndView) {
		String email = (String)session.getAttribute("email");

		if (email == null) {
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		List<ReservationInfo> reservationInfos = reservationService.getReservationInfoResponse(email);

		Map<String, List<ReservationInfo>> reservationInfoResponse = groupingByReservationStatus(reservationInfos);
		modelAndView.addObject("reservationGroupByStatus", reservationInfoResponse);
		modelAndView.addObject("totalCount", reservationInfos.size());
		modelAndView.setViewName("myreservation");
		return modelAndView;
	}

	/**
	 * 취소 여부(cancelYn), 현재 날짜 전, 후에 따라 ReservationInfo를 분류
	 * @param reservationInfos
	 * @return groupedReservation 3가지로 그룹화된 reservation (취소완료, 예약 후 관람 전/후)
	 */
	private Map<String, List<ReservationInfo>> groupingByReservationStatus(List<ReservationInfo> reservationInfos) {
		Map<Boolean, List<ReservationInfo>> groupByCancelYn = reservationInfos.stream()
			.collect(Collectors.partitioningBy(ReservationInfo::isCancelYn));
		Map<Boolean, List<ReservationInfo>> groupByDate = groupByCancelYn.get(false).stream()
			.collect(Collectors.partitioningBy(
				reservationInfo -> reservationInfo.getReservationDate().compareTo(LocalDate.now().toString()) >= 0));

		Map<String, List<ReservationInfo>> groupedReservation = new HashMap<>();
		groupedReservation.put("canceled", groupByCancelYn.get(true));
		groupedReservation.put("confirmed", groupByDate.get(true));
		groupedReservation.put("used", groupByDate.get(false));
		return groupedReservation;
	}

	/**
	 * List<ProductPrice>의 할인된 가격 중 가장 작은 값을 반환
	 * @param prices
	 * @return discounted min Price
	 */
	private int calculateMinPrice(List<ProductPrice> prices) {
		// XXX exception? -> price가 0이면 data 오류이기 때문
		ProductPrice minPrice = prices.stream().reduce((prev, next) -> {
			return (calculateDiscountPrice(prev) < calculateDiscountPrice(next)) ? prev : next;
		}).orElseThrow(IllegalArgumentException::new);

		return calculateDiscountPrice(minPrice);
	}

	/**
	 * 할인된 가격을 반환
	 * @param productPrice 
	 */
	private int calculateDiscountPrice(ProductPrice productPrice) {
		return (int)(productPrice.getPrice() * (1 - productPrice.getDiscountRate() / 100));
	}

	/**
	 * 명세서에 따라 1~5일 후의 무작위 날짜, 현재와 5일 후 날짜를 반환 
	 * @return
	 */
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
