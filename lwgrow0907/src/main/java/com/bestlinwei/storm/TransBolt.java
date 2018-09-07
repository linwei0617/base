package com.bestlinwei.storm;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 传递到下一个bolt
 * @author linwei
 *
 */
public class TransBolt extends BaseBasicBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		//获取上一个组件所声明的Field
		String msg = input.getStringByField("source");
		System.out.println("transBolt get input word is " + msg);
		//进行传递给下一个bolt
		collector.emit(new Values(msg));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("trans"));
	}

}
