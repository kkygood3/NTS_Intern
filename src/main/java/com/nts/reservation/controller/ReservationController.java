package com.nts.reservation.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;

	private static final String PROJECT_ROOT = "C:/Users/USER/git/pjt05";
	private static final String DIRECTORY = "/src/main/webapp/img/comment";
	/**
	 * 예약 확인페이지 url 맵핑
	 * 
	 * @return
	 */
	@GetMapping
	public String getReservation() {
		return "myreservation";
	}

	/**
	 * 리뷰작성 페이지에 필요한 정보 담아서 url 맵핑한다
	 * 
	 * @param productId 리뷰쓸 상품
	 * @param model     표시할 정보
	 * @return 뷰이름 리턴
	 */
	@GetMapping("/{reservationInfoId}/comment")
	public String getComment(ModelMap model,
			@PathVariable(name = "reservationInfoId", required = true) long reservationInfoId) {
		model.addAttribute("productInfo", productService.getProductInfoByReservationInfoId(reservationInfoId));
		return "reviewWrite";
	}

	/**
	 * 리뷰 등록한다
	 * 
	 * @return
	 */
	@PostMapping("/{productId}/comment")
	public String postComment(@PathVariable(name = "productId", required = true) long productId,
			@RequestParam("file") MultipartFile file) {
		String fileName = getFileName(productId, file.getContentType());
		String saveFileName = getDirectory() + "/" + fileName;
		try {
			file.transferTo(new File(saveFileName));
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
//		return "redirect:detail/{displayInfoId}/review";
		return "main";
	}
	
	private String getDirectory() {
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String path = PROJECT_ROOT + DIRECTORY + "/" + today;
		File dir = new File(path);
		System.out.println(path);
		dir.isDirectory();
		dir.mkdir();
		return path;
	}
	
	private String getFileName(long productId, String type) {
		return productId + "_" + new Date().getTime() + "." + type.split("/")[1];
	}
}
