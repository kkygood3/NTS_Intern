package com.nts.reservation.compare;

import java.util.Comparator;

import com.nts.reservation.dto.ReservationDisplayItem;

public class AscendingCancelDate implements Comparator<ReservationDisplayItem> {
	@Override
	public int compare(ReservationDisplayItem r1, ReservationDisplayItem r2) {
		long d1 = r1.getModifyDate().getTime();
		long d2 = r2.getModifyDate().getTime();
		return (int)(d1 - d2);
	}
}