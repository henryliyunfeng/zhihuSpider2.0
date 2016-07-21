package comSpider.worker;


import java.util.logging.Logger;

import comSpider.fetcher.PageFetcher;
import comSpider.handler.ContentHandler;
import comSpider.model.FetchedPage;
import comSpider.model.SpiderParams;
import comSpider.parser.ContentParser;
import comSpider.parser.ZhihuQuestions;
import comSpider.queue.UrlQueue;
import comSpider.storage.DataStorage;

public class SpiderWorker implements Runnable {
	private static final Logger Log = Logger.getLogger(SpiderWorker.class.getName());
	private ContentHandler handler;
	private ContentParser parser;
	private DataStorage store;
	private int threadIndex;
	private ZhihuQuestions targetData;
	
	public SpiderWorker(int threadIndex){
		this.threadIndex = threadIndex;
		this.handler = new ContentHandler();
		this.parser = new ContentParser();
		this.store = new DataStorage();
		this.targetData=null;
	}
	
	@Override
	public void run() {
		// ��¼
		
		
		// ����ץȡURL���в�Ϊ��ʱ��ִ����ȡ����
		// ע�� ����������Ϊ��ʱ��Ҳ����ȡ�����Ѿ�������
		//     ��Ϊ�п�����UrlQueue��ʱ�գ�����worker�̻߳�û�н��µ�URL�������
		//	        ���ԣ�������������ȴ�ʱ�䣬�ٽ���ץȡ�����λ��ᣩ
		while(!UrlQueue.isEmpty()){
			// �Ӵ�ץȡ��������URL
			String url = UrlQueue.removeFirstElement();
			
			// ץȡURLָ����ҳ�棬������״̬���ҳ�����ݹ��ɵ�FetchedPage����
			FetchedPage fetchedPage = PageFetcher.getContentFromUrl(url);
			
			// �����ȡҳ��ĺϷ��ԣ������Ƿ񱻽�ֹ
			if(!handler.check(fetchedPage)){
				// �л�IP�Ȳ���
				// TODO
				
				Log.info("Spider-" + threadIndex + ": switch IP to ");
				continue;
			}
			
			// ����ҳ�棬��ȡĿ������
			targetData=parser.parse(fetchedPage);
			// �洢Ŀ�����ݵ����ݴ洢����DB,�����ļ�txt�����洢����ȡ��Url��VisitedUrlQueue
			store.store(targetData);
			
			// delay
			try {
				Thread.sleep(SpiderParams.DEYLAY_TIME);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		PageFetcher.close();
		Log.info("Spider-" + threadIndex + ": stop...");
	}
}
