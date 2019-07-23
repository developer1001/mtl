package com.zgc.mtl.common.enu;
/**
 * 星期
 * @date 2019-07-23 15:45:39
 * @author yang
 */
public enum Week {
	/**
	 * 周一
	 */
	MON, 
	/**
	 * 周二
	 */
	TUES,
	/**
	 * 周三
	 */
	WED,
	/**
	 * 周四
	 */
	THUR,
	/**
	 * 周五
	 */
	FRI,
	/**
	 * 周六
	 */
	SAT,
	/**
	 * 周日
	 */
	SUN;
	
	public static String getDay(Week week) {
		String day = "wrong day!";
		switch (week) {
		case MON:
			day = "monday";
			break;
		case TUES:
			day = "tuesday";
			break;
		case WED:
			day = "wednesday";
			break;
		case THUR:
			day = "thursday";
			break;
		case FRI:
			day = "friday";
			break;
		case SAT:
			day = "saturday";
			break;
		case SUN:
			day = "sunday";
			break;
		default:
			break;
		}
		return day;
	}
}
