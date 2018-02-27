package com.wlt;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ExecutorService pool = new ThreadPoolExecutor(1,1,60, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World!" );

        wltThread thread = new wltThread();
        pool.execute(thread);
        pool.shutdown();
        while(true){
            if(pool.isTerminated()){
                break;
            }
        }
        System.out.println("主线程结束");
        System.out.println("=====================");
        Thread thread1 = new Thread(new wltThread());
        thread1.start();
        thread1.join();
        System.out.println("单独主线程结束");
    }

}
class wltThread implements Runnable{
    @Override
    public void run() {
        System.out.println("准备休眠5s");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("自线程运行完毕");
    }
}
