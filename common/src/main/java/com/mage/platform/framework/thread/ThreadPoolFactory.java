package com.mage.platform.framework.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.mage.param.resp.ZteResponse;

/**
 * 多线程处理器对象
 * 
 * @author 网络
 * 
 *        文章学习： http://www.iteye.com/topic/1118660
 * 
 *        corePoolSize： 线程池维护线程的最少数量 maximumPoolSize：线程池维护线程的最大数量 keepAliveTime：
 *        线程池维护线程所允许的空闲时间 unit： 线程池维护线程所允许的空闲时间的单位 workQueue： 线程池所使用的缓冲队列
 *        handler： 线程池对拒绝任务的处理策略
 * 
 * 
 *        当线程池中的线程数量大于
 *        corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 *        unit可选的参数为java.util.concurrent.TimeUnit中的几个静态属性：
 *        NANOSECONDS、MICROSECONDS、MILLISECONDS、SECONDS。
 * 
 * 
 *        handler有四个选择： ThreadPoolExecutor.AbortPolicy()
 *        抛出java.util.concurrent.RejectedExecutionException异常
 *        ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务，他会自动重复调用execute()方法 //达到列队的最大上限、同时达到线程的最大数量时，交给主线程执行此程序
 *        ThreadPoolExecutor.DiscardOldestPolicy() 抛弃旧的任务
 *        ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务
 * 
 * 
 *        ///////////////////////////////////////////////=====================
 *        开辟多线程处理器========================================= 举例说明： TaskThreadPool
 *        taskThreadPool = new TaskThreadPool(new Task(dataLog){ public
 *        ZteResponse execute(ZteRequest zteRequest) { try{ DataLog dataLog =
 *        (DataLog)zteRequest; IDataLogManager dataLogManager =
 *        SpringContextHolder.getBean("datasLogManager");
 *        dataLogManager.insertDataLog(dataLog); }catch(Exception e){
 *        e.printStackTrace(); } return new ZteResponse(); } });
 *        ThreadPoolFactory.singleExecute(taskThreadPool); //异步单线程执行
 *        
 */
public class ThreadPoolFactory {

	private static int coreSize = 5;
	private static int orderstandingcoreSize = 2;
	private static int orderstandingmaxPoolSize =5;
	private static int maxPoolSize = 25;
	private static int seconds = 10;
	private static int quenesize = 10;
	
	private static ThreadPoolExecutor singgeExecutor;
	private static ThreadPoolExecutor defExecutor;
	private static ThreadPoolExecutor orderExecutor; // 订单的单独拿出来
	
	private static ThreadPoolExecutor orderStandingExecutor; 
	private static ExecutorService newFixedThreadPool;

	private static ExecutorService newCachedThreadPool;

	private static ExecutorService schedulePool;

	static {
		singgeExecutor = ThreadPoolFactory.getSingleThreadPoolExector();
		defExecutor = ThreadPoolFactory.getDefaultThreadPoolExector();
		orderExecutor = ThreadPoolFactory.getOrderThreadPoolExector();
		orderStandingExecutor = ThreadPoolFactory.getOrderStandingExecutor();
		newFixedThreadPool = Executors.newFixedThreadPool(16);
		newCachedThreadPool = Executors.newCachedThreadPool();
		schedulePool = Executors.newScheduledThreadPool(1);
	}
	
	public static final String EXECTOR_NEW = "newFixedThreadPool";
	public static final String EXECTOR_CACHE = "newCachedThreadPool";
	public static final String EXECTOR_DEF = "defCachedThreadPool";
	public static final String EXECTOR_SINGLE = "singCachedThreadPool";
	
	private static int corePoolsize = 10;

	private static int maximumPoolsize = 100;

	private static long keepAliveTime = 60;

	private static long timeout = 10000;
	
	
	/**
	 * 
	 * 1、定义ExecutorService getExector
	 * 2、for循环执行业务
	 * 3、执行关闭处理
	 * @param type
	 * @param args
	 * @return
	 */
	// 定义执行器
	public static ThreadPoolExecutor getExector(String type, Object... args) {
		ThreadPoolExecutor executorService = null;
		int thread_count =corePoolsize;
		if(args !=null && args.length>0)
			thread_count = new Integer((Integer) args[0]).intValue();
		if (EXECTOR_NEW.equals(type)) { // 创建一个可重用固定线程集合的线程池，以共享的无界队列方式来运行这些线程，按此总方式执行，利用缓冲执行优化，能支持最大线程数+任务列队数
			return (ThreadPoolExecutor) Executors.newFixedThreadPool(thread_count); //列队大小为整数最大值、默认启动固定的线程，不会根据失效时间自动销毁，任务执行完成调用关闭按钮后则线程销毁、列队大小为整数最大值（一次执行的记录不能超过整数最大值）。
		} else if (EXECTOR_CACHE.equals(type)) { //创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们，按此种方式执行，性能高，能支持最大线程数的任务数执行
			executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
			executorService.setKeepAliveTime(keepAliveTime,TimeUnit.MILLISECONDS); //使用的缓存队列为SynchronousQueue ，对列为阻塞
			return executorService;
		}else if (EXECTOR_DEF.equals(type)) {
			executorService = new ThreadPoolExecutor(200,Integer.MAX_VALUE, 5 * 60, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());//MAX_VALUE在列队满后才会开最大线程池
			return executorService;
		}
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(1); //单线程模式
	}

	/**
	 * 执行线程对象
	 * @param taskThreadPool
	 */
	public static ZteResponse submit(TaskThreadPool taskThreadPool,ThreadPoolExecutor executorService) {
		executorService.execute(taskThreadPool);
		return taskThreadPool.getZteResponse();
	}
	
	// 关闭执行器
	public static void closeThreadPool(ExecutorService executor) {
		executor.shutdown();
	}
	
	public static ThreadPoolExecutor getSingleThreadPoolExector() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, seconds,
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000),
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	public static ThreadPoolExecutor getDefaultThreadPoolExector() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,
				maxPoolSize, seconds, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(quenesize),
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	public static ThreadPoolExecutor getOrderThreadPoolExector() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,
				maxPoolSize, seconds, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(quenesize),
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
	
	public static ThreadPoolExecutor getOrderStandingExecutor() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(orderstandingcoreSize,
				orderstandingmaxPoolSize, seconds, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(quenesize),
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
	
	public static ThreadPoolExecutor getThreadPoolExector(int coreSize,
			int maxPoolSize, int seconds, int quenesize) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,
				maxPoolSize, seconds, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(quenesize),
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	/**
	 * 执行线程对象
	 * 
	 * @param taskThreadPool
	 */
	public static ZteResponse singleExecute(TaskThreadPool taskThreadPool) {
		singgeExecutor.execute(taskThreadPool);
		return taskThreadPool.getZteResponse();
	}

	/**
	 * 执行线程对象
	 * 
	 * @param taskThreadPool
	 */
	public static ZteResponse fixedExecute(TaskThreadPool taskThreadPool) {
		newFixedThreadPool.execute(taskThreadPool);
		
		return taskThreadPool.getZteResponse();
	}

	/**
	 * 执行线程对象
	 * 
	 * @param taskThreadPool
	 */
	public static ZteResponse cacheExecute(TaskThreadPool taskThreadPool) {

		newCachedThreadPool.execute(taskThreadPool);
		return taskThreadPool.getZteResponse();
	}

	/**
	 * 执行线程对象
	 * 
	 * @param taskThreadPool
	 */
	public static ZteResponse defExecute(TaskThreadPool taskThreadPool) {
		defExecutor.execute(taskThreadPool);
		return new ZteResponse();
	}

	/**
	 * 订单执行线程对象
	 * @param taskThreadPool
	 */
	public static ZteResponse orderExecute(TaskThreadPool taskThreadPool) {
		orderExecutor.execute(taskThreadPool);
		return taskThreadPool.getZteResponse();
	}

	public static void main(String[] args) { //按顺序执行列队信息
		
	}
}
