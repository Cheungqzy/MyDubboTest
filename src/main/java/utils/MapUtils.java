package utils;

import java.math.BigDecimal;

/**
 * Created by Cheungqzy on 2017/9/14.
 */
public class MapUtils {
    //private static double EARTH_RADIUS = 6378.137;
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double lat1, double lng1, double lat2, double lng2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args) {
        System.out.println((new BigDecimal(0).compareTo(new BigDecimal(-2)) > -1));
        //大屯里  116.419737 40.010556
        // 小营路 116.430331 39.998067
        //位置
        Double longitude=116.4301886099,latitude = 39.9992763317;
        System.out.println(MapUtils.GetDistance(latitude,longitude,40.010556,116.419737));
        System.out.println(MapUtils.GetDistance(latitude,longitude,39.998067,116.430331));
    }
}