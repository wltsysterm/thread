package com.wlt.notifywait;

import java.util.concurrent.*;

/**
 * 测试类
 * @author 徐景洋
 */
public class Test {
    //创建一个包含指定数目线程的线程池，如果任务数量多于线程数量，那么没有执行的任务必须等待，直到有任务完成为止。
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    //创建单一线程池 此方法可以创建单一线程池，线程池里只有一个线程，单一线程池可以实现以队列的方式来执行任务
    private static ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
    //创建的是无界线程池，可以进行线程自动回收，此类线程池中存放线程个数理论值为Integer.MAX_VALUE最大值
    private static ExecutorService threadPool2 = Executors.newCachedThreadPool();



//    private ThreadFactory factory = new threadfac
    private static ExecutorService t = new ThreadPoolExecutor(2, 2,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    public static void main(String[] args) {
        Goods goods=new Goods();
        
        //生产者生产商品
        Producer p=new Producer();
        p.setGoods(goods);
        
        //消费者取走商品
        Customer c=new Customer();
        c.setGoods(goods);

        threadPool.execute(new Thread(p));
        threadPool.execute(new Thread(c));
        System.out.println(threadPool.isShutdown());
        threadPool.shutdown();
//        new Thread(p).start();
//        new Thread(c).start();

    }
}