package com.nts.dao.displayinfodao;


/**
*
* @description : DisplayInfoDaoSqls
* @package : com.nts.dao.displayinfodao
* @filename : DisplayInfoDaoSqls.java
* @author : 최석현
* 
*/
public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID = "SELECT "
																			+ "  di.id"
																			+ ", c.name as category_name "
																			+ ", c.id as category_id "
																			+ ", p.id as product_id "
																			+ ", di.opening_hours "
																			+ ", di.place_name "
																			+ ", di.place_lot "
																			+ ", di.place_street "
																			+ ", di.tel "
																			+ ", di.homepage "
																			+ ", di.email "
																			+ ", p.content "
																			+ ", p.event "
																			+ ", p.description "
																			+ ", p.create_date "
																			+ ", p.modify_date "
																		+ "FROM "
																			+ "  display_info di "
																			+ ", product p "
																			+ ", category c "
																		+ "WHERE "
																			+ "    di.id = :displayInfoId "
																			+ "and di.product_id = p.id "
																			+ "and c.id = p.category_id ";
	
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID = "SELECT "
																				+ "  fi.content_type "
																				+ ", fi.create_date "
																				+ ", fi.delete_flag "
																				+ ", di.id as display_info_id "
																				+ ", diimg.id as display_info_image_id "
																				+ ", fi.id as file_id "
																				+ ", fi.file_name "
																				+ ", fi.modify_date "
																				+ ", fi.save_file_name as product_image_url "
																			+ "FROM "
																				+ "  display_info di "
																				+ ", display_info_image diimg "
																				+ ", file_info fi "
																			+ "WHERE "
																				+ "    diimg.display_info_id = :displayInfoId "
																				+ "and diimg.display_info_id = di.id "
																				+ "and diimg.file_id = fi.id ";
}
