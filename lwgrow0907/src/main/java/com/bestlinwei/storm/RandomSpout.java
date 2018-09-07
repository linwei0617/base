package com.bestlinwei.storm;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 随机数据源spout
 * @author linwei
 *
 */
public class RandomSpout extends BaseRichSpout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//发射数据的工具类
	private SpoutOutputCollector collector;
	
	private String[] data = new String[] {"java","php","c","c++","mq","js"};

	Random random = new Random();
	
	 /**

     * 在SpoutTracker类中被调用，每调用一次就可以向storm集群中发射一条数据（一个tuple元组），该方法会被不停的调用

     */
	@Override
	public void nextTuple() {
        try {
	        	String msg = data[random.nextInt(5)];
	    		//调用发射方法
	    		collector.emit(new Values(msg));
	    		 //模拟等待1000ms
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化collector
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
     * 定义字段id，该id在简单模式下没有用处，但在按照字段分组的模式下有很大的用处。
     * 该declarer变量有很大作用，我们还可以调用declarer.declareStream();来定义stramId，该id可以用来定义更加复杂的流拓扑结构
     */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("source"));//collector.emit(new Values(msg));参数要对应
	}

}
