package comSpider.queue;

import java.util.LinkedList;

public class VisitedUrlQueue {
	//���Ѿ����ʹ���url��һ�����ݽṹ����ʱ�ö���ʵ��
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
