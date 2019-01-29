package com.nts.constant;

public class DaoQueries {
	public static final String CATEGORY_API_SELECT_ALL = "SELECT * FROM category_api_view";

	public static final String CATEGORY_SELECT_ALL = "SELECT * FROM category";

	public static final String PRODUCT_API_SELECT_ALL = "SELECT * FROM product_api_view ORDER BY product_id DESC limit :start, :limit";
	public static final String PRODUCT_API_SELECT_CATEGORY = "SELECT * FROM product_api_view where category_id = :category_id ORDER BY product_id DESC limit :start, :limit";
	public static final String PRODUCT_API_SELECT_COUNT = "SELECT count(*) FROM product_api_view where category_id = :category_id";
	public static final String PRODUCT_API_SELECT_COUNT_ALL = "SELECT count(*) FROM product_api_view";

	public static final String PRODUCT_SELECT_ALL = "SELECT * FROM product ORDER BY id DESC";
	public static final String PRODUCT_SELECT_BY_ID = "SELECT * FROM product where id = :id";
	public static final String PRODUCT_SELECT_PAGING = "SELECT * FROM product ORDER BY id DESC limit :start, :limit";
	public static final String PRODUCT_SELECT_COUNT = "SELECT count(*) FROM product";

	public static final String PROMOTION_API_SELECT_ALL = "SELECT * FROM promotion_api_view";
	public static final String PROMOTION_API_SELECT_COUNT = "SELECT count(*) FROM promotion_api_view";

	public static final String PROMOTION_SELECT_ALL = "SELECT * FROM promotion";
	public static final String PROMOTION_SELECT_COUNT = "SELECT count(*) FROM promotion";
}
