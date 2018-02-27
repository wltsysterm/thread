package com.wlt;

import com.wlt.notifywait.Test;

import java.util.concurrent.*;

/**
 * @author 魏霖涛
 * @since 2018/2/27 0027
 */
public class ExecuteSubmit{
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            Callable callable = new WltCallable("正常");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<TestObj> future =  executor.submit(callable);
            executor.shutdown();
            //Callable接口支持返回执行结果，此时需要调用FutureTask.get()方法实现，此方法会阻塞主线程直到获取‘将来’结果；当不调用此方法时，主线程不会阻塞！
            TestObj testObj = future.get();
            System.out.println(testObj.toString());
            System.out.println("主线程结束");
        }
}
class WltCallable implements Callable{
    private String status;
    public WltCallable(String status){
        this.status = status;
    }
    @Override
    public Object call() throws Exception {
        System.out.println("进入calla方法");
        System.out.println("开始休眠5s");
        Thread.sleep(5000);
        System.out.println(status);
        return new TestObj("wlt","11");
    }
}
class TestObj{
    private String name;
    private String age;

    public TestObj(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
