/**
 * 
 */
package com.neu.info7255.demo.config;
import redis.clients.jedis.Jedis;
/**
 * @author richabhatia
 *
 */
public class MyRedisConnection {
	
	private static Jedis jedis;

	public static Jedis getConnection() {

		if (jedis == null) {
			Jedis jedis = new Jedis("localhost");
			System.out.println("Connected to server sucessfully");
			return jedis;
		}

		return jedis;

	}
}
