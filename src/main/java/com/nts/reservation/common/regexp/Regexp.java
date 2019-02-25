/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.regexp;

public class Regexp {

	public static final String EMAIL = "[.\\-\\w]+@\\w+[.]?[\\-\\w]+[.]?[\\w]+";

	public static final String Tel = "01[01789]-\\d{3,4}-\\d{4}";

	public static final String DATE = "[12][0-9]{3}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";

}
