package comSpider;


import comSpider.fetcher.PageFetcher;
import comSpider.model.FetchedPage;
import comSpider.model.SpiderParams;
import comSpider.parser.ContentParser;
import comSpider.queue.UrlQueue;
import comSpider.worker.SpiderWorker;

public class Main {
	public static ContentParser parsed=new ContentParser();
	public static void main(String[] args){
		// ��ʼ�����ò���
		//initializeParams();
		
		// ��ʼ������ȡ����
		initializeQueue();
		
		// ����worker�̲߳�����
		for(int i = 1; i <= SpiderParams.WORKER_NUM; i++){
			new Thread(new SpiderWorker(i)).start();
		}
		
//	     //���߳�����100����
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//             e.printStackTrace();
//        }
	}
	/**
	 * ׼����ʼ����ȡ����
	 */
	private static void initializeQueue(){
		//ץȡ֪����ҳ��������Ϣ,����"https://www.zhihu.com/question/39174915#answer-27470654"��ַ ����question�ֶεġ�
		//UrlQueue.addElement("https://www.zhihu.com/explore");
		String url="https://www.zhihu.com/explore";
		FetchedPage fetchedPage=PageFetcher.getContentFromUrl(url);
		parsed.parseLinks(fetchedPage);
		System.out.println(UrlQueue.tostring());
	}
}
