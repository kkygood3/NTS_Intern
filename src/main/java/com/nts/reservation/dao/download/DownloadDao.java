package com.nts.reservation.dao.download;

import com.nts.reservation.dto.download.DownloadInfo;

public interface DownloadDao {
	DownloadInfo selectDownloadInfo(int reservationInfoImageId);
}
