package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationPageInfoDaoSqls.*;
import static com.nts.reservation.dao.sql.ReservationPageInfoDaoSqls.SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.dao.sql.ReservationPageInfoDaoSqls.UPDATE_CANCEL_FLAG_TO_FALSE_BY_RESERVATION_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.*;
import static com.nts.reservation.property.Const.RESERVATION_EMAIL;
import static com.nts.reservation.property.Const.RESERVATION_INFO_ID;
import static com.nts.reservation.property.Const.START;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.service.util.SqlHelper;

/**
 * 예약관련 DB접근 클래스
 * @author si yoon
 *
 */
@Repository
public class ReservationInfoDao extends BaseDao {
	private RowMapper<ReservationPageInfo> reservationPageInfoRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationPageInfo.class);
	private RowMapper<ReservationDisplayItem> reservationDisplayItemRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationDisplayItem.class);
	private SimpleJdbcInsert insertAction;

	ReservationInfoDao(DataSource dataSource) {
		super(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");
	}

	/**
	 * 상품에 맞는 예약페이지화면에 표시할 정보 셀렉트
	 * @param displayInfoId 상품정보
	 * @return 예약페이지 화면 정보
	 */
	public ReservationPageInfo selectReservationPageInfoByDisplayInfoId(long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID, params,
			reservationPageInfoRowMapper);
	}

	/**
	 * 예약정보 등록
	 * @param reservationInfo 사용자가 입력한 예약정보
	 * @return 예약id
	 */
	public long insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}

	/**
	 * 해당 email로 예약한 내역 월단위로 페이징해서 셀렉트
	 * @param reservationEmail 예약자 이메일
	 * @param start 시작 (현재로부터 start개월 후)
	 * @param end 끝 (현재로부터 end개월 후)
	 * @return 예약정보
	 */
	public List<ReservationDisplayItem> selectReservationInfoByReservationEmail(String reservationEmail, int start,
		int limit, String condition) {
		Map<String, Object> params = new HashMap<>();
		params.put(RESERVATION_EMAIL, reservationEmail);
		params.put(START, start);
		params.put(LIMIT, limit);
		
		return getJdbc().query(
			SqlHelper.getSqlStatement(SELECT_RESERVATION_DISPLAY_LIST_ITEM_BY_RESERVATION_EMAIL_WITH_CONDITION, CONDITION, condition),
			params, reservationDisplayItemRowMapper);
	}

	/**
	 * 예약취소
	 * @param reservationInfoId 취소할 예약 id
	 * @param reservationEmail 예약한 email
	 * @return 성공여부
	 */
	public int updateCancelFlagToFalseByReservationInfoId(long reservationInfoId, String reservationEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put(RESERVATION_INFO_ID, reservationInfoId);
		params.put(RESERVATION_EMAIL, reservationEmail);
		return getJdbc().update(UPDATE_CANCEL_FLAG_TO_FALSE_BY_RESERVATION_INFO_ID, params);
	}
}
