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
		// 初始化配置参数
		//initializeParams();
		
		// 初始化待爬取队列
		initializeQueue();
		
		// 创建worker线程并启动
		for(int i = 1; i <= SpiderParams.WORKER_NUM; i++){
			new Thread(new SpiderWorker(i)).start();
		}
		
//	     //主线程休眠100毫秒
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//             e.printStackTrace();
//        }
	}
	/**
	 * 准备初始的爬取链接
	 */
	private static void initializeQueue(){
		//抓取知乎首页的提问信息,形如"https://www.zhihu.com/question/39174915#answer-27470654"网址 带有question字段的。
		//UrlQueue.addElement("https://www.zhihu.com/explore");
		String url="https://www.zhihu.com/explore";
		FetchedPage fetchedPage=PageFetcher.getContentFromUrl(url);
		parsed.parseLinks(fetchedPage);
		System.out.println(UrlQueue.tostring());
	}
}
