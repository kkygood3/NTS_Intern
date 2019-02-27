package com.nts.reservation.dao.download;

import com.nts.reservation.dto.download.DownloadInfo;

/**
 * /download 요청에 대응
 */
public interface DownloadDao {
	/**
	 * comment image id로 다운로드 목표 file의 정보 조회 
	 * @param reservationInfoImageId
	 * @return
	 */
	DownloadInfo selectDownloadInfo(int reservationInfoImageId);
}
