package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileInfoService;

@RestController
@RequestMapping("/file_info")
public class FileInfoApiController {
	@Autowired
	private FileInfoService fileInfoService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<String> getProductCountAndThumbnailInfos(@RequestParam(name = "product_id", required = true) int productId,
		@RequestParam(name = "type", required = false, defaultValue = "ma") String type) {
		return fileInfoService.getSaveFileName(productId, type);
	}
}
