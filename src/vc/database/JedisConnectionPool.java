
package vc.database;
 
import redis.clients.jedis.Jedis; 
import redis.clients.jedis.JedisPool; 
import redis.clients.jedis.JedisPoolConfig; 
import redis.clients.jedis.exceptions.JedisConnectionException; 
import vc.WSConfig;
 
 
public class JedisConnectionPool  { 
  

    static JedisPool jedisPool; 
     
    public JedisConnectionPool() { 
     jedisPool = null; 
    } 
 


    
    public synchronized Jedis getJedisConnection() {  // koushik: removed static 
        try { 
            if (jedisPool == null) { 
                JedisPoolConfig config = new JedisPoolConfig(); 
                config.setMaxTotal(10); 
			    config.setMaxIdle(5); 
			    config.setMinIdle(1); 
			    config.setMaxWaitMillis(3000); 
			     
                jedisPool = new JedisPool(config,WSConfig.redisHost, WSConfig.redisPort); 
                 
            } 
            return jedisPool.getResource(); 
        } catch (JedisConnectionException e) { 
            System.err.println("Could not establish Redis connection. Is the Redis running?"); 
            throw e; 
        } 
    } 
 
    public synchronized void close(Jedis resource) {  // koushik: removed static, added code to increase robustness 
        if (jedisPool != null) { 
		   try { 
		    if (resource != null) { 
		     jedisPool.returnResource(resource); 
		     resource = null; 
		    } 
		   } catch (JedisConnectionException e) { 
		    jedisPool.returnBrokenResource(resource); 
		    resource = null; 
		   } finally { 
		    if (resource != null)  
		     jedisPool.returnResource(resource); 
		     resource = null; 
		   } 
		 } 
    } 
}
