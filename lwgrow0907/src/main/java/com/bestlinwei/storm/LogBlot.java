package com.bestlinwei.storm;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class LogBlot extends BaseBasicBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String msg = input.getStringByField("trans");
		System.out.println("LogBlot get input word is " + msg);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

}
