package com.woyou.springcucumber.listeners;

import org.testng.IExecutionListener;

public class BootListener implements IExecutionListener {

    @Override
    public void onExecutionStart(){
        System.out.println("--- on execution start --- ");
    }


    @Override
    public void onExecutionFinish(){
        System.out.println("--- on execution finish --- ");
    }

}
