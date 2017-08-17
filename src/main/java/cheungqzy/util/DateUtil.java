package cheungqzy.util;

import com.google.common.base.Preconditions;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 日期操作工具类，主要实现了日期的常用操作。
 * <p/>
 * 在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.
 * <p/>
 * 格式的意义如下： 日期和时间模式 <br>
 * 日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
 * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
 * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
 * <p/>
 * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
 * <table>
 * <tr>
 * <td>字母</td>
 * <td>日期或时间元素</td>
 * <td>表示</td>
 * <td>示例</td>
 * <td></tr>
 * <tr>
 * <td>G</td>
 * <td>Era</td>
 * <td>标志符</td>
 * <td>Text</td>
 * <td>AD</td>
 * <td></tr>
 * <tr>
 * <td>y</td>
 * <td>年</td>
 * <td>Year</td>
 * <td>1996;</td>
 * <td>96</td>
 * <td></tr>
 * <tr>
 * <td>M</td>
 * <td>年中的月份</td>
 * <td>Month</td>
 * <td>July;</td>
 * <td>Jul;</td>
 * <td>07</tr>
 * <tr>
 * <td>w</td>
 * <td>年中的周数</td>
 * <td>Number</td>
 * <td>27</td>
 * <td></tr>
 * <tr>
 * <td>W</td>
 * <td>月份中的周数</td>
 * <td>Number</td>
 * <td>2</td>
 * <td></tr>
 * <tr>
 * <td>D</td>
 * <td>年中的天数</td>
 * <td>Number</td>
 * <td>189</td>
 * <td></tr>
 * <tr>
 * <td>d</td>
 * <td>月份中的天数</td>
 * <td>Number</td>
 * <td>10</td>
 * <td></tr>
 * <tr>
 * <td>F</td>
 * <td>月份中的星期</td>
 * <td>Number</td>
 * <td>2</td>
 * <td></tr>
 * <tr>
 * <td>E</td>
 * <td>星期中的天数</td>
 * <td>Text</td>
 * <td>Tuesday;</td>
 * <td>Tue</tr>
 * <tr>
 * <td>a</td>
 * <td>Am/pm</td>
 * <td>标记</td>
 * <td>Text</td>
 * <td>PM</td>
 * <td></tr>
 * <tr>
 * <td>H</td>
 * <td>一天中的小时数（0-23）</td>
 * <td>Number</td>
 * <td>0</tr>
 * <tr>
 * <td>k</td>
 * <td>一天中的小时数（1-24）</td>
 * <td>Number</td>
 * <td>24</td>
 * <td></tr>
 * <tr>
 * <td>K</td>
 * <td>am/pm</td>
 * <td>中的小时数（0-11）</td>
 * <td>Number</td>
 * <td>0</td>
 * <td></tr>
 * <tr>
 * <td>h</td>
 * <td>am/pm</td>
 * <td>中的小时数（1-12）</td>
 * <td>Number</td>
 * <td>12</td>
 * <td></tr>
 * <tr>
 * <td>m</td>
 * <td>小时中的分钟数</td>
 * <td>Number</td>
 * <td>30</td>
 * <td></tr>
 * <tr>
 * <td>s</td>
 * <td>分钟中的秒数</td>
 * <td>Number</td>
 * <td>55</td>
 * <td></tr>
 * <tr>
 * <td>S</td>
 * <td>毫秒数</td>
 * <td>Number</td>
 * <td>978</td>
 * <td></tr>
 * <tr>
 * <td>z</td>
 * <td>时区</td>
 * <td>General</td>
 * <td>time</td>
 * <td>zone</td>
 * <td>Pacific</td>
 * <td>Standard</td>
 * <td>Time;</td>
 * <td>PST;</td>
 * <td>GMT-08:00</tr>
 * <tr>
 * <td>Z</td>
 * <td>时区</td>
 * <td>RFC</td>
 * <td>822</td>
 * <td>time</td>
 * <td>zone</td>
 * <td>-0800</td>
 * <td></tr>
 * </table>
 * <p/>
 * 模式字母通常是重复的，其数量确定其精确表示：
 */

/**
 * @author
 */
public final class DateUtil implements Serializable {
    public static final DateTimeFormatter DEFAULT_HOUR_MINUTE_FORMATTER = DateTimeFormat.forPattern(
            "HH:mm");
    /**
     *
     */
    private static final long serialVersionUID = -3098985139095632110L;
    private static final String DEFAULT_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_STRING = "yyyy-MM-dd";
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormat.forPattern(
            DEFAULT_TIME_FORMAT_STRING);


    private DateUtil() {
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        Preconditions.checkNotNull(localDate);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        Preconditions.checkNotNull(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalDate();
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        Preconditions.checkNotNull(localDateTime);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        Preconditions.checkNotNull(date);

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 格式化日期显示格式yyyy-MM-dd
     *
     * @param sdate 原始日期格式
     * @return yyyy-MM-dd格式化后的日期显示
     */
    public static String dateFormat(String sdate) {
        return dateFormat(sdate, "yyyy-MM-dd");
    }

    /**
     * 格式化日期显示格式yyyyMMddHHmmss
     *
     * @param sdate 原始日期格式
     * @return yyyyMMdd格式化后的日期显示
     */
    public static String dateFormatT(String sdate) {
        return dateFormat(sdate, "yyyyMMddHHmmss");
    }

    /**
     * 格式化日期显示格式
     *
     * @param sdate  原始日期格式
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
    public static String dateFormat(String sdate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        java.sql.Date date = java.sql.Date.valueOf(sdate);
        String dateString = formatter.format(date);

        return dateString;
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd 起始日期，格式yyyy-MM-dd
     * @param ed 终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
                .valueOf(sd)).getTime())
                / (3600 * 24 * 1000);
    }

    /**
     * 起始年月yyyy-MM与终止月yyyy-MM之间跨度的月数
     *
     * @return int
     */
    public static int getInterval(String beginMonth, String endMonth) {
        int intBeginYear = Integer.parseInt(beginMonth.substring(0, 4));
        int intBeginMonth = Integer.parseInt(beginMonth.substring(beginMonth
                .indexOf("-") + 1));
        int intEndYear = Integer.parseInt(endMonth.substring(0, 4));
        int intEndMonth = Integer.parseInt(endMonth.substring(endMonth
                .indexOf("-") + 1));

        return ((intEndYear - intBeginYear) * 12)
                + (intEndMonth - intBeginMonth) + 1;
    }

    public static Date getDate(String sDate, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);

        return fmt.parse(sDate, pos);
    }

    /**
     * 取得当前日期的年份，以yyyy格式返回.
     *
     * @return 当年 yyyy
     */
    public static String getCurrentYear() {
        return getFormatCurrentTime("yyyy");
    }

    /**
     * 取得当前日期的月份，以MM格式返回.
     *
     * @return 当前月份 MM
     */
    public static String getCurrentMonth() {
        return getFormatCurrentTime("MM");
    }

    /**
     * 取得当前日期的天数，以格式"dd"返回.
     *
     * @return 当前月中的某天dd
     */
    public static String getCurrentDay() {
        return getFormatCurrentTime("dd");
    }


    /**
     * 取得当期日期的年，月，以格式"yyyy-MM"返回
     *
     * @return
     */
    public static String getCurrentYM() {
        return getFormatCurrentTime("yyyy-MM");
    }

    /**
     * 返回当前时间字符串。
     * <p/>
     * 格式：yyyy-MM-dd
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentDate() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd");
    }

    /**
     * 返回给定时间字符串。
     * <p/>
     * 格式：yyyy-MM-dd
     *
     * @param date 日期
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDate(Date date) {
        return getFormatDateTime(date, "yyyy-MM-dd");
    }

    /**
     * 返回当前时间字符串。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentTime() {
        return getFormatDateTime(new Date(), DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 返回给定时间字符串。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatTime(Date date) {
        return getFormatDateTime(date, DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 根据给定的格式，返回时间字符串。
     * <p/>
     * 格式参照类描绘中说明.
     *
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatCurrentTime(String format) {
        return getFormatDateTime(new Date(), format);
    }

    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
     *
     * @param date   指定的日期
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        Preconditions.checkNotNull(date, "日期不能为空");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date).trim();
    }

    /**
     * 取得指定年月日的日期对象.
     *
     * @param year  年
     * @param month 月注意是从1到12
     * @param day   日
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * 取得指定分隔符分割的年月日的日期对象.
     *
     * @param args  格式为"yyyy-MM-dd"
     * @param split 时间格式的间隔符，例如“-”，“/”
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(String args, String split) {
        String[] temp = args.split(split);
        int year = new Integer(temp[0]);
        int month = new Integer(temp[1]);
        int day = new Integer(temp[2]);
        return getDateObj(year, month, day);
    }

    public static String formatDate(Date theDate) {
        Locale locale = Locale.CHINA;
        String dateString = "";
        if (theDate == null) return "";
        try {
            Calendar cal = Calendar.getInstance(locale);
//            cal.setFirstDayOfWeek(Calendar.SUNDAY);
            cal.setTime((Date) theDate);

            //DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM,locale);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING, locale);
            dateString = dateFormatter.format(cal.getTime());
            //System.out.println(dateString);
            //System.out.println(cal.get(Calendar.YEAR));
            //System.out.println(cal.get(cal.DAY_OF_WEEK));
        } catch (Exception e) {
            System.out.println("test result:" + e.getMessage());
        } finally {

        }
        return dateString;
    }

    /**
     * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
     *
     * @param dateStr 日期描述
     * @param pattern 日期模式
     * @return 给定字符串描述的日期对象。
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    /**
     * 取得当前Date对象.
     *
     * @return Date 当前Date对象.
     */
    public static Date getDateObj() {
        Calendar c = new GregorianCalendar();
        return c.getTime();
    }

    /**
     * 取得当前系统时间戳.
     *
     * @return String 当前系统时间戳.
     */
    public static String getTimestamp() {
        Calendar c = new GregorianCalendar();
        return String.valueOf(c.getTime().getTime());
    }

    public static int getDaysOfCurMonth() {
        int curyear = new Integer(getCurrentYear()); // 当前年份
        int curMonth = new Integer(getCurrentMonth());// 当前月份
        int mArray[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31};
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 根据制定的年月 返回当前月份有多少天
     *
     * @param time
     * @return
     */
    public static int getDaysOfCurMonth(final String time) {
        String[] timeArray = time.split("-");
        int curyear = new Integer(timeArray[0]); // 当前年份
        int curMonth = new Integer(timeArray[1]);// 当前月份
        int mArray[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31};
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        if (curMonth == 12) {
            return mArray[0];
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 返回制定为年度为year月度为month的月份内，第weekOfMonth个星期的第dayOfWeek天。<br>
     * 00 00 00 01 02 03 04 <br>
     * 05 06 07 08 09 10 11<br>
     * 12 13 14 15 16 17 18<br>
     * 19 20 21 22 23 24 25<br>
     * 26 27 28 29 30 31 <br>
     * 2006年的第一个周的1到7天为：05 06 07 01 02 03 04 <br>
     * 2006年的第二个周的1到7天为：12 13 14 08 09 10 11 <br>
     * 2006年的第三个周的1到7天为：19 20 21 15 16 17 18 <br>
     * 2006年的第四个周的1到7天为：26 27 28 22 23 24 25 <br>
     * 2006年的第五个周的1到7天为：02 03 04 29 30 31 01 。本月没有就自动转到下个月了。
     *
     * @param year        形式为yyyy <br>
     * @param month       形式为MM,参数值在[1-12]。<br>
     * @param weekOfMonth 在[1-6],因为一个月最多有6个周。<br>
     * @param dayOfWeek   数字在1到7之间，包括1和7。1表示星期天，7表示星期六<br>
     *                    -6为星期日-1为星期五，0为星期六 <br>
     * @return <type>int</type>
     */
    public static int getDayofWeekInMonth(String year, String month,
            String weekOfMonth, String dayOfWeek) {
        Calendar cal = new GregorianCalendar();
        // 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。
        int y = new Integer(year);
        int m = new Integer(month);
        cal.clear();// 不保留以前的设置
        cal.set(y, m - 1, 1);// 将日期设置为本月的第一天。
        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, new Integer(weekOfMonth));
        cal.set(Calendar.DAY_OF_WEEK, Integer.parseInt(dayOfWeek));
        // System.out.print(cal.get(Calendar.MONTH)+" ");
        // System.out.print("当"+cal.get(Calendar.WEEK_OF_MONTH)+"\t");
        // WEEK_OF_MONTH表示当天在本月的第几个周。不管1号是星期几，都表示第一个周
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param year
     * @param month month是从1开始的12结束
     * @param day
     * @return
     */
    public static int getDayOfWeek(String year, String month, String day) {
        Calendar cal = new GregorianCalendar(new Integer(year),
                new Integer(month) - 1, new Integer(day));
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回制定日期所在的周是一年中的第几个周。<br>
     * created by wangmj at 20060324.<br>
     *
     * @param year
     * @param month 范围1-12<br>
     * @param day
     * @return int
     */
    public static int getWeekOfYear(String year, String month, String day) {
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(new Integer(year),
                new Integer(month) - 1, new Integer(day));
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date   给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static Date getDateAdd(Date date, int amount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date   给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @param format 输出格式.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(Date date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期固定间隔天数的日期，如前60天dateAdd(-60)
     *
     * @param amount 距今天的间隔日期长度，向前为负，向后为正
     * @param format 输出日期的格式.
     * @return java.lang.String 按照格式输出的间隔的日期字符串.
     */
    public static String getFormatCurrentAdd(int amount, String format) {

        Date d = getDateAdd(new Date(), amount);

        return getFormatDateTime(d, format);
    }

    /**
     * 取得给定格式的昨天的日期输出
     *
     * @param format 日期输出的格式
     * @return String 给定格式的日期字符串.
     */
    public static String getFormatYestoday(String format) {
        return getFormatCurrentAdd(-1, format);
    }

    /**
     * 返回指定日期的前一天。<br>
     *
     * @param sourceDate
     * @return
     */
    public static String getYestoday(String sourceDate, String format) {
        return getFormatDateAdd(getDateFromString(sourceDate, format), -1,
                format);
    }

    /**
     * 返回明天的日期，<br>
     *
     * @return
     */
    public static String getFormatTomorrow(String format) {
        return getFormatCurrentAdd(1, format);
    }

    /**
     * 返回指定日期的后一天。<br>
     *
     * @param sourceDate
     * @return
     */
    public static String getFormatDateTommorrow(String sourceDate, String format) {
        return getFormatDateAdd(getDateFromString(sourceDate, format), 1,
                format);
    }

    public static String getCurrentDateString(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(cal.getTime()).trim();
    }

    /**
     * 获取当前日期
     *
     * @param format
     * @return
     */
    /*
     * public static java.util.Date getCurDateByFormat(String format) { Date
	 * newdate = new Date(System.currentTimeMillis()); SimpleDateFormat sdf =
	 * new SimpleDateFormat(format); String strDate = sdf.format(newdate);
	 * java.util.Date date = null; try { date = sdf.parse(strDate); } catch
	 * (ParseException ex) { // log.error("转化字符串到日期出错" + ex); } return date; }
	 */

    /**
     * 返回当前时间串 格式:yyMMddhhmmss,在上传附件时使用
     *
     * @return String
     */
    public static String getCurDate() {
        GregorianCalendar gcDate = new GregorianCalendar();
        int year = gcDate.get(GregorianCalendar.YEAR);
        int month = gcDate.get(GregorianCalendar.MONTH) + 1;
        int day = gcDate.get(GregorianCalendar.DAY_OF_MONTH);
        int hour = gcDate.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = gcDate.get(GregorianCalendar.MINUTE);
        int sen = gcDate.get(GregorianCalendar.SECOND);
        String y;
        String m;
        String d;
        String h;
        String n;
        String s;
        y = Integer.toString(year);

        if (month < 10) {
            m = "0" + Integer.toString(month);
        } else {
            m = Integer.toString(month);
        }

        if (day < 10) {
            d = "0" + Integer.toString(day);
        } else {
            d = Integer.toString(day);
        }

        if (hour < 10) {
            h = "0" + Integer.toString(hour);
        } else {
            h = Integer.toString(hour);
        }

        if (minute < 10) {
            n = "0" + Integer.toString(minute);
        } else {
            n = Integer.toString(minute);
        }

        if (sen < 10) {
            s = "0" + Integer.toString(sen);
        } else {
            s = Integer.toString(sen);
        }

        return "" + y + m + d + h + n + s;
    }

    /**
     * 根据给定的格式，返回时间字符串。
     *
     * @param format
     * @return
     */
    public static String getCurTimeByFormat(String format) {
        Date newdate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(newdate);
    }

    /**
     * 获取两个时间串时间的差值，单位为秒
     *
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static long getDiff(String startTime, String endTime) {
        long diff = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);
            Date startDate = simpleDateFormat.parse(startTime);
            Date endDate = simpleDateFormat.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }

    /**
     * 获取小时/分钟/秒
     *
     * @param second 秒
     */
    public static String getHour(long second) {
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;

        return hour + "小时" + minute + "分钟" + sec + "秒";

    }

    /**
     * 返回指定时间字符串。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getDateTime(long microsecond) {
        return getFormatDateTime(new Date(microsecond), DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 返回当前时间加实数小时后的日期时间。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return Float 加几实数小时.
     */
    public static String getDateByAddFltHour(float flt) {
        int addMinute = (int) (flt * 60);
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.MINUTE, addMinute);
        return getFormatDateTime(cal.getTime(), DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 返回指定时间加实数小时后的日期时间。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return Float 加几实数小时.
     */
    public static String getDateByAddFltHour(String dateStr, float flt) {
        int addMinute = (int) (flt * 60);
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDateFromString(dateStr, DEFAULT_TIME_FORMAT_STRING));
        cal.add(GregorianCalendar.MINUTE, addMinute);
        return getFormatDateTime(cal.getTime(), DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 返回指定时间减实数小时后的日期时间。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return Float 减几实数小时.
     */
    public static String getDateByMinusFltHour(String dateStr, float flt) {
        int addMinute = -(int) (flt * 60);
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDateFromString(dateStr, DEFAULT_TIME_FORMAT_STRING));
        cal.add(GregorianCalendar.MINUTE, addMinute);
        return getFormatDateTime(cal.getTime(), DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 返回指定时间减实数分钟后的日期时间。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return int 减几实数小时.
     */
    public static String getDateByMinusFlt(String dateStr, int flt) {
        int addMinute = -(int) (flt);
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDateFromString(dateStr, DEFAULT_TIME_FORMAT_STRING));
        cal.add(GregorianCalendar.MINUTE, addMinute);
        return getFormatDateTime(cal.getTime(), DEFAULT_TIME_FORMAT_STRING);
    }

    /**
     * 返回指定时间加指定分钟数后的日期时间。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 时间.
     */
    public static String getDateByAddMinite(String datetime, int minute) {
        String returnTime = null;
        Calendar cal = new GregorianCalendar();
        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);
            date = simpleDateFormat.parse(datetime);
            cal.setTime(date);
            cal.add(GregorianCalendar.MINUTE, minute);
            returnTime = getFormatDateTime(cal.getTime(), DEFAULT_TIME_FORMAT_STRING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnTime;

    }

    /**
     * 返回指定时间加指定分钟数后的日期时间。
     * <p/>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 时间.
     */
    public static Date getDateByAddMinite(Date datetime, int minute) {
        Calendar cal = new GregorianCalendar();
        try {
            cal.setTime(datetime);
            cal.add(GregorianCalendar.MINUTE, minute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cal.getTime();

    }

    /**
     * 返回指定时间加指定小时数后的日期时间。
     *
     * @param date
     * @param hour
     * @return Date
     */
    public static Date getDateByAddHour(Date date, int hour) {
        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        cal.add(GregorianCalendar.HOUR_OF_DAY, hour);


        return cal.getTime();

    }

    /**
     * 获取两个时间串时间的差值，单位为小时
     *
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(小时)
     */
    public static int getIntHour(String endTime, String startTime) {
        long diff = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);
            Date endDate = simpleDateFormat.parse(endTime);
            Date startDate = simpleDateFormat.parse(startTime);
            diff = endDate.getTime() - startDate.getTime();
            diff = diff / (1000 * 60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Long(diff).intValue();
    }

    /**
     * 获取两个时间串时间的差值，单位为小时
     *
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(小时)
     */
    public static float getDiffHour(String endTime, String startTime) {
        float diff = 0F;
        SimpleDateFormat ft = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);
        try {
            Date endDate = ft.parse(endTime);
            Date startDate = ft.parse(startTime);
            diff = endDate.getTime() - startDate.getTime();
            diff = diff / (1000 * 60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 获取两个时间类型的时间的差值，单位为小时
     *
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static int getDiffHour(Date startTime, Date endTime) {
        long diff = 0;
        diff = endTime.getTime() - startTime.getTime();
        diff = diff / (1000 * 60 * 60);
        return new Long(diff).intValue();
    }

    /**
     * 生成格式化日期，为了进行日期比较
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromString(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    /**
     * 获取和当前时间相隔几天的时间串
     *
     * @param days 相隔天数
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateByCurrent(int days) {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, days);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);
        return simpleDateFormat.format(cal.getTime());
    }


    public static String getStringToSting(String date, String fromat1, String format2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat1);
        Date resDate = sdf.parse(date);
        sdf = new SimpleDateFormat(format2);
        return sdf.format(resDate).trim();
    }

    public static void main(String[] args) throws Exception {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        String y = "2013-11-05";
        String z = "2013-12-06";
        Date yy = sdf.parse(y);
        Date zz = sdf.parse(z);
        System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        int d = (int) ((zz.getTime() - yy.getTime()) / (24 * 60 * 60 * 1000));
        System.out.println(d);
    }

    /**
     * 根据一周的第几天数字返回：周一至周日的中文
     *
     * @param dayOfWeek 一周的第几天，1-7
     * @return 中文的周几
     */
    public static String findChineseDayOfWeek(int dayOfWeek) {
        String[] days = new String[]{"", "一", "二", "三", "四", "五", "六", "日"};
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new IllegalArgumentException("一周的第几天不能是:"+dayOfWeek);
        }

        return "周" + days[dayOfWeek];
    }

    /**
     * 根据任意时间，返回规整的期望的配送时间
     *
     * @param dateTime 时间
     * @return
     */
    public static DateTime roundDateTime(DateTime dateTime) {
        if (dateTime.minuteOfHour().get() >= 10 && dateTime.minuteOfHour().get() <= 40) {
            dateTime = dateTime.withTime(dateTime.getHourOfDay(), 30, 0, 0);
        } else if (dateTime.minuteOfHour().get() < 10) {
            dateTime = dateTime.withTime(dateTime.getHourOfDay(), 0, 0, 0);
        } else {
            dateTime = dateTime.plusHours(1);
            dateTime = dateTime.withTime(dateTime.getHourOfDay(), 0, 0, 0);
        }

        return dateTime;
    }
}
