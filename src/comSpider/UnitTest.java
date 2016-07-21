package comSpider;

import comSpider.fetcher.PageFetcher;
import comSpider.handler.ContentHandler;
import comSpider.model.FetchedPage;
import comSpider.parser.ContentParser;
import comSpider.storage.DataStorage;

public class UnitTest {
	public static void main(String[] args) {
		String url="https://www.zhihu.com/explore";
		ContentHandler handler=new ContentHandler();
		ContentParser parser=new ContentParser();
		DataStorage store=new DataStorage();
		
		//测试获取网页是否成功
		FetchedPage fetched= PageFetcher.getContentFromUrl(url);
		//System.out.println(fetched.toString());
		System.out.println("-----------------------");
		parser.parse(fetched);
		//parser.parse(fetched);
	}
}
