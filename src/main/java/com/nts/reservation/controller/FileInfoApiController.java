package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.FileInfoService;

@RestController
@RequestMapping("product/{productId}/file_info")
public class FileInfoApiController {
	@Autowired
	private FileInfoService fileInfoService;

	@GetMapping("/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getProductCountAndThumbnailInfos(
		@PathVariable(name = "productId", required = true) int productId,
		@PathVariable(name = "type", required = true) String type) {
		return fileInfoService.getSaveFileName(productId, type);
	}
}
