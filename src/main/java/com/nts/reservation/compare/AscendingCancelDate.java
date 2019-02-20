package com.nts.reservation.compare;

import java.util.Comparator;

import com.nts.reservation.dto.ReservationDisplayItem;

/**
 * 최근에 취소한 순서로 정렬 
 * @author si yoon
 *
 */
public class AscendingCancelDate implements Comparator<ReservationDisplayItem> {
	@Override
	public int compare(ReservationDisplayItem r1, ReservationDisplayItem r2) {
		long d1 = r1.getModifyDate().getTime();
		long d2 = r2.getModifyDate().getTime();
		
		long interval = d2 - d1;
		if (interval > 0) {
			return 1;
		} else if (interval < 0) {
			return -1;
		}
		return 0;
	}
}