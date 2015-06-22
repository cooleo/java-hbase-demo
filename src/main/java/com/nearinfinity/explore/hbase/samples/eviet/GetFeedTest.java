package com.nearinfinity.explore.hbase.samples.eviet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;

import static com.nearinfinity.explore.hbase.samples.fakenames.FakenamesConstants.CREDITCARD_FAMILY;

public class GetFeedTest {

    private static final String TABS = "\t\t\t\t";

    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "btestfeed");
        conf.set("hbase.zookeeper.quorum", "localhost");

        Get get = new Get(Bytes.toBytes("feed12346"));
        get.addFamily(FeedInfoConstants.FEEDINFO_FAMILY);
        Result result = table.get(get);
        List<Cell> cells = result.listCells();
        for (Cell cell : cells) {
            String columnQualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
            String columnValue = Bytes.toString(CellUtil.cloneValue(cell));
            String cloneFamily = Bytes.toString(CellUtil.cloneFamily(cell));
            long columnTimestamp = cell.getTimestamp();
            System.out.printf("column=%s, timestamp=%d, value=%s\n",
                     columnQualifier, columnTimestamp, columnValue);
        }


//        NavigableMap<byte[], NavigableMap<byte[],NavigableMap<Long,byte[]>>> map = result.getMap();

        table.close();
    }
}
