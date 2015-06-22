package com.nearinfinity.explore.hbase.samples.eviet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * Created by hungnguyendang on 6/23/15.
 */
public class CreateTable {
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "localhost");
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("btestfeed"));
        tableDescriptor.addFamily(new HColumnDescriptor("info"));
        tableDescriptor.addFamily(new HColumnDescriptor("category"));
        tableDescriptor.addFamily(new HColumnDescriptor("country"));
        tableDescriptor.addFamily(new HColumnDescriptor("star"));


        admin.createTable(tableDescriptor);
        boolean tableAvailable = admin.isTableAvailable("btestfeed");
        System.out.println("tableAvailable = " + tableAvailable);
    }
}
