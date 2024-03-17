package com.lane.interview.algorithm_workbook.p17_系统设计题;

/**
 * @ Author:  duenpu
 * @ Date  :  12:08 2024/3/17
 */
public class Q04_redislua限流 {

    // 固定计数

    /*
    local key = "rate.limit:" .. KEYS[1] --限流KEY
    local limit = tonumber(ARGV[1])        --限流大小
    local current = tonumber(redis.call('get', key) or "0")
    if current + 1 > limit then --如果超出限流大小
    return 0
          else  --请求数+1，并设置1秒过期
    redis.call("INCRBY", key,"1")
            redis.call("expire", key,"1")
            return current + 1
    end
    */

    // 滑动窗口

//    当有请求来的时候记录时间戳，统计窗口内请求的数量时只需要统计redis中记录的数量。可以使用redis中的zset结构来存储。key可以设置为请求的资源名，
//    同时根据限流的对象，往key中加入限流对象信息。比如根据ip限制访问某个资源的流量，可以使用方法名+ip作为key。score设置为时间戳。value则可以根据请求参数等信息生成MD5，或者直接生成UUID来存入，
//    防止并发时多个请求存入的score和value一样导致只存入一个数据。
//
//    步骤如下：
//
//    定义时间窗口
//    请求到来，丢弃时间窗口之外的数据，ZREMRANGEBYSCORE KEYS[i], -inf, window_start
//    判断时间窗口内的请求个数是否达到阈值。ZCARD KEYS[i] 要小于阈值
//    如果小于则通过zadd加入，超过则返回不放行

    // lua实现
    /*local window_start = tonumber(ARGV[1])- tonumber(ARGV[2])
    redis.call('ZREMRANGEBYSCORE', KEYS[1], '-inf', window_start)
    local current_requests = redis.call('ZCARD', KEYS[1])
    if current_requests < tonumber(ARGV[3]) then
    redis.call('ZADD', KEYS[1], tonumber(ARGV[1]), ARGV[4])
            return 1
            else
            return 0
    end*/

    // 代码实现
    /**
     * redis限流操作类
     * 每个请求都以其发生的时间戳作为分数(SCORE)存储在集合中。通过移除旧于当前时间窗口的请求来维护滑动窗口。通过检查集合中的元素数量，以确定是否超过了设定的最大请求数。
     */
  /*  @Component
    public class RedisLimitUtil {
        @Autowired
        private RedisTemplate<String, Object> redisTemplate;
        // 滑动时间窗口大小
        private static final long WINDOW_SIZE_IN_SECONDS = 1000;

        *//**
         * 判断是否限流
         * 这里不考虑超过long最大值的情况，系统在达到long最大值前就奔溃了。
         * reuqestId 请求id,可以是uuid不重复即可
         *//*
        public boolean isLimited(String key, String reuqestId, long countLimit) {
            // 使用Redis的多个命令来实现滑动窗口
            // 移除窗口之外的旧请求。
            redisTemplate.zremrangeByScore(key, 0, currentTimeMillis - WINDOW_SIZE_IN_SECONDS);
            // 获取当前窗口内的请求数量
            long count = redisTemplate.zcard(key);

            if (countLimit >= count) {
                // 将新请求添加到集合中
                redisTemplate.zadd(key, currentTimeMillis, reuqestId);
                return true;
            } else {
                return false;
            }
        }
    }*/


/*    // 定义切点之前的操作
    @Before("rateLimiter() || rateLimiters()")
    public void doBefore(JoinPoint point) {
        try {
            // 从切点获取方法签名
            MethodSignature signature = (MethodSignature) point.getSignature();
            // 获取方法
            Method method = signature.getMethod();
            String name = point.getTarget().getClass().getName() + "." + signature.getName();
            // 获取日志注解
            RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
            RateLimiters rateLimiters = method.getAnnotation(RateLimiters.class);

            List<RateLimiter> limiters = new ArrayList<>();
            if (ObjectUtils.isNotNull(rateLimiter)) {
                limiters.add(rateLimiter);
            }

            if (ObjectUtils.isNotNull(rateLimiters)) {
                limiters.addAll(Arrays.asList(rateLimiters.value()));
            }

            if (!allowRequest(limiters, name)) {
                throw new ServiceException("访问过于频繁，请稍候再试");
            }

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("服务器限流异常，请稍候再试");
        }
    }

    *//**
     * 是否允许请求
     *
     * @param rateLimiters 限流注解
     * @param name         方法全名
     * @return 是否放行
     *//*
    private boolean allowRequest(List<RateLimiter> rateLimiters, String name) {
        List<String> keys = getKeys(rateLimiters, name);
        Object[] args = getArgs(rateLimiters);
        Object res = redisTemplate.execute(limitScript, keys, args);

        return ObjectUtils.isNotNull(res) && (Long) res == 1L;
    }

    *//**
     * 获取限流的键
     *
     * @param rateLimiters 限流注解
     * @param name         方法全名
     * @return
     *//*
    private List<String> getKeys(List<RateLimiter> rateLimiters, String name) {
        List<String> keys = new ArrayList<>();

        for (RateLimiter rateLimiter : rateLimiters) {
            String key = rateLimiter.key();
            RateLimitRule[] rules = rateLimiter.rules();
            LimitType type = rateLimiter.type();

            StringBuilder sb = new StringBuilder();
            sb.append(key).append(name);

            if (LimitType.IP == type) {
                String ipAddr = IpUtils.getIpAddr();
                sb.append("_").append(ipAddr);
            } else if (LimitType.USER == type) {
                Long userId = SecurityUtils.getUserId();
                sb.append("_").append(userId);
            }
            for (RateLimitRule rule : rules) {
                int time = rule.time() * 1000;
                int count = rule.count();
                StringBuilder builder = new StringBuilder(sb);
                builder.append("_").append(time).append("_").append(count);
                keys.add(builder.toString());
            }
        }
        return keys;
    }

    *//**
     * 获取需要的参数
     *
     * @param rateLimiters 限流注解
     * @return
     *//*
    private Object[] getArgs(List<RateLimiter> rateLimiters) {
        List<Object> args = new ArrayList<>();
        args.add(System.currentTimeMillis());
        for (RateLimiter rateLimiter : rateLimiters) {
            RateLimitRule[] rules = rateLimiter.rules();
            for (RateLimitRule rule : rules) {
                int time = rule.time() * 1000;
                int count = rule.count();
                args.add(time);
                args.add(count);
                args.add(IdUtils.fastSimpleUUID());
            }
        }
        return args.toArray();
    }*/

    /*

    public static boolean accquire() throws IOException, URISyntaxException {
        Jedis jedis = new Jedis("127.0.0.1");
        File luaFile = new File(RedisLimitRateWithLUA.class.getResource("/").toURI().getPath() + "limit.lua");
        String luaScript = FileUtils.readFileToString(luaFile);

        String key = "ip:" + System.currentTimeMillis()/1000; // 当前秒
        String limit = "5"; // 最大限制
        List<String> keys = new ArrayList<String>();
        keys.add(key);
        List<String> args = new ArrayList<String>();
        args.add(limit);
        Long result = (Long)(jedis.eval(luaScript, keys, args)); // 执行lua脚本，传入参数
        return result == 1;
    }

    */
}
