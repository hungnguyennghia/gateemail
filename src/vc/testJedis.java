
package vc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
import vc.util.DateProc;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 
public class testJedis extends Thread{
 
    //address of your redis server
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
 
    //the jedis connection pool..
    private static JedisPool pool = null;
 
    public testJedis() {
        //configure our pool connection
        pool = new JedisPool(redisHost, redisPort);
 
    }
 
    public void addSets() {
        //let us first add some data in our redis server using Redis SET.
        String key = "members";
        String member1 = "Sedarius";
        String member2 = "Richard";
        String member3 = "Joe";
 
        //get a jedis connection jedis connection pool
        Jedis jedis = pool.getResource();
        try {
            //save to redis
            jedis.sadd(key, member1, member2, member3);
 
            //after saving the data, lets retrieve them to be sure that it has really added in redis
            Set members = jedis.smembers(key);
        
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }
 
    public void addHash() {
        //add some values in Redis HASH
        String key = "javapointers";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Java Pointers");
        map.put("domain", "www.javapointers.com");
        map.put("description", "Learn how to program in Java");
 
        Jedis jedis = pool.getResource();
        try {
            //save to redis
            jedis.hmset(key, map);
 
            //after saving the data, lets retrieve them to be sure that it has really added in redis
            Map<String, String> retrieveMap = jedis.hgetAll(key);
            for (String keyMap : retrieveMap.keySet()) {
                System.out.println(keyMap + " " + retrieveMap.get(keyMap));
            }
 
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }
    
    public static void main(String[] args){
    	 
    	testJedis main = new testJedis();
//    	for (int i = 0; i < 10; i++) {
//            main.addSets();
//            main.addHash();
//           try {
//			sleep(5*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
    	System.err.println(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()) );
    	
        Jedis jedis = pool.getResource();
        try {
String key="";
		  	for (int i = 0; i < 1000000; i++) {
//			  	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort); 
//			  	jedisPool = new JedisConnectionPool(config,WSConfig.redisHost, WSConfig.redisPort); 
		  		key="hungnguyennghia"+i;
			  	if(!jedis.exists(key)){
				  	jedis.set(key, "1");
				  	jedis.expire(key, 30);
//				  	System.out.println("set key...");
			  	}else{
//			  		System.out.println(jedis.get(key));
//			  		System.out.println("exists key...");
			  	}
//			  	jedis.close();
			  	//new JedisConnectionPool().close(jedis);
			  	//sleep(1000);
			}
		  	System.err.println(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()) );
        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }

    }
}