package com.nearinfinity.explore.hbase.samples.eviet;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nearinfinity.explore.hbase.samples.fakenames.FakenamesConstants.CREDITCARD_FAMILY;
import static com.nearinfinity.explore.hbase.samples.fakenames.FakenamesConstants.PERSONAL_FAMILY;
import static com.nearinfinity.explore.hbase.samples.fakenames.FakenamesConstants.PERSONNUMBER_QUALIFIER;

/**
 * Created by hungnguyendang on 6/23/15.
 */
public class AddFeedInfo {
    private static Random rand = new Random(System.currentTimeMillis());
    private static String[] expYears = {"2016", "2017", "2018", "2019", "2020"};

    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "btestfeed");
        conf.set("hbase.zookeeper.quorum", "localhost");


        String title  = "Thời gian thắm thoát thoi đưa đẩy thích là chích chứ ngênh ngán gì đâu thằng ";
        String description = "hello feed descritpion";
        String thumb = "http://thumb.png";
        int feedType = 3;

        String hostName = "hostname.com";
        String redirectUrl = "http://redirecturl.com";

        ArrayList<String> countries = new ArrayList<>();
        countries.add("Vietnam");
        countries.add("US");


        ArrayList<String> categories = new ArrayList<>();
        categories.add("Phim Hanh Dong");
        categories.add("Tình cảm xã hội");
        categories.add("Tâm lý hành động");


        ArrayList<String> stars = new ArrayList<>();
        stars.add("Jetli");
        stars.add("Jason Statham");



        Put put = new Put(Bytes.toBytes("feed12346"));
        put.add(FeedInfoConstants.FEEDINFO_FAMILY, FeedInfoConstants.FEED_TITLE_QULIFIER, Bytes.toBytes(title));
        put.add(FeedInfoConstants.FEEDINFO_FAMILY, FeedInfoConstants.FEED_DESRIPTION_QULIFIER, Bytes.toBytes(description));
        put.add(FeedInfoConstants.FEEDINFO_FAMILY, FeedInfoConstants.FEED_THUMB_QULIFIER, Bytes.toBytes(thumb));
        put.add(FeedInfoConstants.FEEDINFO_FAMILY, FeedInfoConstants.FEED_HOSTNAME_QULIFIER, Bytes.toBytes(hostName));
        put.add(FeedInfoConstants.FEEDINFO_FAMILY, FeedInfoConstants.FEED_REDIRECTURL_QULIFIER, Bytes.toBytes(redirectUrl));
        put.add(FeedInfoConstants.FEEDINFO_FAMILY, FeedInfoConstants.FEED_TYPE_QULIFIER, Bytes.toBytes(feedType));
        for(int i = 0; i<countries.size();i++){
            String iString = String.format("%d",i);
            byte[] invid =  Bytes.toBytes(iString);
            put.add(FeedInfoConstants.MOVIE_COUNTRY_QUALIFIER, invid, Bytes.toBytes(countries.get(i)));
        }

        for(int i = 0; i<categories.size();i++){
            String iString = String.format("%d",i);
            byte[] invid =  Bytes.toBytes(iString);
            put.add(FeedInfoConstants.MOVIE_CATEGORY_QULIFIER, invid, Bytes.toBytes(categories.get(i)));
        }
        for(int i = 0; i<stars.size();i++){
            String iString = String.format("%d",i);
            byte[] invid =  Bytes.toBytes(iString);
            put.add(FeedInfoConstants.MOVIE_STAR_QUALIFIER, invid, Bytes.toBytes(stars.get(i)));
        }

        table.put(put);
        table.flushCommits();

//        Get get = new Get(Bytes.toBytes("cochran-julie-f-248495"));
//        get.addFamily(CREDITCARD_FAMILY);
//        Result result = table.get(get);
//        List<Cell> cells = result.listCells();
//        for (Cell cell : cells) {
//            String columnQualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
//            String columnValue = Bytes.toString(CellUtil.cloneValue(cell));
//            long columnTimestamp = cell.getTimestamp();
//            System.out.printf("column=%s:%s, timestamp=%d, value=%s\n",
//                    Bytes.toString(CREDITCARD_FAMILY), columnQualifier, columnTimestamp, columnValue);
//        }

        table.close();
    }

    private static String buildExpirationYear() {
        int yearIndex = rand.nextInt(expYears.length);
        return expYears[yearIndex];
    }

    private static String buildExpirationMonth() {
        int month = rand.nextInt(12) + 1;
        return StringUtils.leftPad(String.valueOf(month), 2, '0');
    }

    private static String buildCvv2() {
        int cvv2 = rand.nextInt(1000);
        return StringUtils.leftPad(String.valueOf(cvv2), 3, '0');
    }

    private static String buildCardNumber() {
        return "4111" + buildLastThreeCreditCardNumberBlocks();
    }

    private static String buildLastThreeCreditCardNumberBlocks() {
        StringBuilder builder = new StringBuilder(16);
        for (int i = 0; i < 3; i++) {
            int block = rand.nextInt(10000);
            String blockStr = StringUtils.leftPad(String.valueOf(block), 4, '0');
            builder.append(blockStr);
        }
        return builder.toString();
    }
}
