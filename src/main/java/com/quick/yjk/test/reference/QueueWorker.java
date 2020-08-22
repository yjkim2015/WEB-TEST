package com.quick.yjk.test.reference;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class QueueWorker<T> extends Thread {
private static Logger LOGGER = LogManager.getLogger(QueueWorker.class);
	
	private final int LOGGING_NUM = 100;
	
    private LinkedList<T> eventLinkedList = new LinkedList<T>();
    private List<T> messageRepository = Collections.synchronizedList(eventLinkedList);

    private Object waitObject = new Object();
    
    private boolean runFlag;
    
    public abstract void processObject(T t);

    private String name;
    private int count;
    
    public QueueWorker(String name) {
    	this.name = name;
    }
    
    public void push(T t) {
    	count++;
    	
    	if (count >= LOGGING_NUM) {
    		LOGGER.info(String.format("[%s] bufferSize:%s", name, messageRepository.size()));
    		count = 0;
    	}
    	
		messageRepository.add(t);
		synchronized (waitObject) {
			waitObject.notify();
		}
    }

    public void run() {
        T t = null;
        runFlag = true;
        
        while ( runFlag ) {
            synchronized ( waitObject ) {
                if ( messageRepository.size() > 0 ) {
                    t = messageRepository.remove(0);
                }
                else {
                    t = null;

                    try {
                        waitObject.wait();
                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                    }
                }
            }

            try {
                if ( t != null ) {
                	try{ 
                		processObject(t);
                	} catch(Exception e ) {
                		e.printStackTrace();
                	}
                }
            }
            catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }
    
    public void close() {
    	runFlag = false;
    	
    	synchronized ( waitObject ) {
    		waitObject.notify();
    	}
    }
	
	public int getBufferSize() {
		return messageRepository.size();
	}
}