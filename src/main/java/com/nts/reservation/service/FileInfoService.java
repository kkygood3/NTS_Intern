package com.nts.reservation.service;

import java.util.List;

public interface FileInfoService {
	public List<String> getSaveFileName(long productId, String type);
}
