package com.nearinfinity.explore.hbase.samples.eviet;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by hungnguyendang on 6/23/15.
 */
public class FeedInfoConstants {
    public static final byte[] FEEDINFO_FAMILY = Bytes.toBytes("info");
    public static final byte[] MOVIEINFO_FAMILY = Bytes.toBytes("movie");

    //feed
    public static final byte[] FEED_TITLE_QULIFIER = Bytes.toBytes("title");
    public static final byte[] FEED_DESRIPTION_QULIFIER = Bytes.toBytes("description");
    public static final byte[] FEED_THUMB_QULIFIER = Bytes.toBytes("thumb");
    public static final byte[] FEED_HOSTNAME_QULIFIER = Bytes.toBytes("hostname");
    public static final byte[] FEED_TYPE_QULIFIER = Bytes.toBytes("type");
    public static final byte[] FEED_REDIRECTURL_QULIFIER = Bytes.toBytes("redirectUrl");
    public static final byte[] FEED_TIME_QUALIFIER = Bytes.toBytes("time");
    public static final byte[] FEED_YEAR_QUALIFIER = Bytes.toBytes("year");



    //movie

    public static final byte[] MOVIE_COUNTRY_QUALIFIER = Bytes.toBytes("country");
    public static final byte[] MOVIE_STAR_QUALIFIER = Bytes.toBytes("star");
    public static final byte[] MOVIE_CATEGORY_QULIFIER = Bytes.toBytes("category");



}
