package com.nts.reservation.reserve.controller;

import java.sql.SQLException;
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
	private static final int NONE = 0;

	/**
	 * Display 별 예약화면으로 이동
	 * @throws SQLException 
	 */
	@GetMapping(path = "/reserve/{displayInfoId}")
	public ModelAndView goReserve(@PathVariable(name = "displayInfoId") int displayInfoId, ModelAndView mav)
		throws SQLException {

		if (displayInfoId < 0) {
			IllegalArgumentException e = new IllegalArgumentException("Can't use Navgative Value!!!");
			LOGGER.warn("Bad Request! Parameter / displayInfoId : {}", displayInfoId, e);
			throw e;
		}

		DisplayResponse displayResponse = displayService.getDisplayInfo(displayInfoId, NONE);

		List<ProductPrice> prices = displayResponse.getProductPrices();
		int minPrice = calculateMinPrice(prices);
		if (minPrice == 0) {
			SQLException e = new SQLException();
			LOGGER.error("Does not Exist product price!! / displayInfoId : {}", displayInfoId, e);
			throw e;
		}

		mav.setViewName("reserve");
		mav.addObject("minPrice", minPrice);
		mav.addObject("dates", computeRandomPeriod());
		mav.addObject("displayResponse", displayResponse);
		return mav;
	}

	/**
	 * session에 email이 저장되어 있다면, email로 저장되어 있는 {@code List<Reservation>}를 반환
	 */
	@GetMapping(path = "/myreservation")
	public ModelAndView goMyReserve(HttpSession session, ModelAndView mav) {
		String email = (String)session.getAttribute("email");

		List<ReservationInfo> reservationInfos = reservationService.getReservationInfoResponse(email);

		Map<String, List<ReservationInfo>> reservationInfoResponse = groupingByReservationStatus(reservationInfos);
		mav.addObject("reservationGroupByStatus", reservationInfoResponse);
		mav.addObject("totalCount", reservationInfos.size());
		mav.setViewName("myreservation");
		return mav;
	}

	/**
	 * 취소 여부(cancelYn), 현재 날짜 전, 후에 따라 ReservationInfo를 분류
	 * @param reservationInfos
	 * @return groupedReservation 3가지로 그룹화된 reservation (취소완료, 예약 후 관람 전/후)
	 */
	private Map<String, List<ReservationInfo>> groupingByReservationStatus(List<ReservationInfo> reservationInfos) {
		return reservationInfos.stream()
			.collect(Collectors.groupingBy(reservationInfo -> {
				if (reservationInfo.isCancelYn()) {
					return "canceled";
				}
				if (reservationInfo.getReservationDate().compareTo(LocalDate.now().toString()) >= 0) {
					return "confirmed";
				} else {
					return "used";
				}
			}));
	}

	/**
	 * List<ProductPrice>의 할인된 가격 중 가장 낮은 가격을 반환
	 * @param prices
	 * @return minPrice 해당 display중 가장 낮은 가격
	 */
	private int calculateMinPrice(List<ProductPrice> prices) {
		if (prices == null || prices.size() == 0) {
			return 0;
		}
		ProductPrice minPrice = prices.stream().reduce((prev, next) -> {
			return (calculateDiscountPrice(prev) < calculateDiscountPrice(next)) ? prev : next;
		}).get();
		return calculateDiscountPrice(minPrice);
	}

	/**
	 * 할인된 가격을 반환
	 * @param productPrice 
	 */
	private int calculateDiscountPrice(ProductPrice productPrice) {
		return (int)Math.round(productPrice.getPrice() * (1 - (productPrice.getDiscountRate() / 100)));
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
