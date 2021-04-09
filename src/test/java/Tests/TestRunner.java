package Tests;

import org.testng.annotations.Factory;

public class TestRunner {

    @Factory
    public Object[] getTestClasses(){
        String env = System.getProperty("browser","chrome");
        String []envs = env.split(",");

        Object[] testObject = new Object[envs.length];
        for(int i=0;i< envs.length;i++){
            testObject[i] = new Executor(envs[i]);
        }
        return testObject;
    }



}
