package comSpider.queue;

import java.util.LinkedList;

public class VisitedUrlQueue {
	//对已经访问过的url建一个数据结构，暂时用队列实现
	private static LinkedList<String> visitedUrlQueue = new LinkedList<String>();
	
	public synchronized static void addElement(String url){
		visitedUrlQueue.add(url);
	}
	
	public synchronized static void addFirstElement(String url){
		visitedUrlQueue.addFirst(url);
	}
	
	public synchronized static String removeFirstElement(){
		return visitedUrlQueue.removeFirst();
	}
	
	public synchronized static boolean isEmpty(){
		return visitedUrlQueue.isEmpty();
	}
	
	public static int size(){
		return visitedUrlQueue.size();
	}
	
	public static boolean isContains(String url){
		return visitedUrlQueue.contains(url);
	}
}
