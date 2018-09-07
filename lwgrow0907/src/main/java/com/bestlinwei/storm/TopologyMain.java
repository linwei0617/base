package com.bestlinwei.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

/**
 * 
 * @author linwei
 *
 */
public class TopologyMain {

	public static void main(String[] args) throws Exception {
		
		Config cfg = new Config();
		cfg.setNumWorkers(2);
		cfg.setDebug(true);
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new RandomSpout());
		builder.setBolt("trans-bolt", new TransBolt()).shuffleGrouping("spout");
		builder.setBolt("log-bolt", new LogBlot()).shuffleGrouping("trans-bolt");
		//1 本地模式
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("top3", cfg, builder.createTopology());
		Thread.sleep(10000);
		cluster.killTopology("top3");
		cluster.shutdown();
		// 集群模式
		//StormSubmitter.submitTopology("top1", cfg, builder.createTopology());
	}
}
