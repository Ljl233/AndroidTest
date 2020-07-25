package com.example.dialogfragmentdemo;

public class ExceptionThread extends Thread {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExceptionThread thread = new ExceptionThread();
            thread.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
