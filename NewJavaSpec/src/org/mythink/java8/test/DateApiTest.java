package org.mythink.java8.test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateApiTest {

	public static void main(String[] args){
		//Clock提供访问当前日期和时间。Clock是对当前时区敏感的，可以用来代替System.currentTimeMillis()来获取当前的毫秒值
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);
		System.out.println(System.currentTimeMillis()-millis);
		System.out.println(legacyDate.toString());
		
		System.out.println(ZoneId.getAvailableZoneIds());
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println(now1.isBefore(now2));
		
		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minusBetween = ChronoUnit.MINUTES.between(now1, now2);
		System.out.println(hoursBetween);
		System.out.println(minusBetween);
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek);
		
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
		System.out.println(xmas);
		
		LocalDateTime sylvester = LocalDateTime.of(2014,Month.DECEMBER,31,23,59,59);
		DayOfWeek dayofwk = sylvester.getDayOfWeek();
		System.out.println("week:"+dayofwk+",month:"+sylvester.getMonth()+",minusofDay:"+sylvester.getLong(ChronoField.MINUTE_OF_DAY));
		Instant inst = sylvester.atZone(ZoneId.systemDefault()).toInstant();
		Date ld = Date.from(inst);
		System.out.println(ld);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");
		LocalDateTime parsed =LocalDateTime.parse("Nov 03, 2014 - 07:13",formatter);
		String string = formatter.format(parsed);
		System.out.println(string);
		
		
	}
}
