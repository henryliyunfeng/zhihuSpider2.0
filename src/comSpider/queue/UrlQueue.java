package comSpider.queue;


import java.util.LinkedList;
public class UrlQueue {
	//模拟一个url进出队列
	private static LinkedList<String> urlQueue = new LinkedList<String>();
	
	public synchronized static void addElement(String url){
		urlQueue.add(url);
	}
	
	public synchronized static void addFirstElement(String url){
		urlQueue.addFirst(url);
	}
	
	public synchronized static String removeFirstElement(){
		return urlQueue.removeFirst();
		
	}
	
	public synchronized static boolean isEmpty(){
		return urlQueue.isEmpty();
	}
	
	public static int size(){
		return urlQueue.size();
	}
	
	public static boolean isContains(String url){
		return urlQueue.contains(url);
	}
	
	public static String tostring(){
		return urlQueue.toString();
	}
}
