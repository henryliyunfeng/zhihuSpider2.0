package comSpider.fetcher;


import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import comSpider.model.FetchedPage;
import comSpider.queue.UrlQueue;

public class PageFetcher {
	private static final Logger Log = Logger.getLogger(PageFetcher.class.getName());
	
	/**
	 * 创建HttpClient实例，并初始化连接参数
	 */
	private static CloseableHttpClient client=HttpClients.createDefault();
	/**
	 * 主动关闭HttpClient连接
	 */
	public static void close(){
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据url爬取网页内容
	 * @param url
	 * @return FetchedPage
	 */
	public  static FetchedPage getContentFromUrl(String url){
		FetchedPage fetchedPage=null;
		
		// 创建Get请求，并设置Header
		HttpGet getHttp = new HttpGet(url);	
		//System.out.println(getHttp.getRequestLine());
		
		//创建响应处理器ResponseHandler,一定要重写handleResponse(HttpResponse response)方法
		ResponseHandler<FetchedPage> responseHandler = new ResponseHandler<FetchedPage>(){

			@Override
			public FetchedPage handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				// TODO Auto-generated method stub
				StatusLine statusLine=response.getStatusLine();//响应的状态行
				int statusCode=statusLine.getStatusCode();//状态码
				//System.out.println(statusCode);
				HttpEntity entity=response.getEntity();//响应的实体
				//如果状态码>=300,抛出响应异常
				if(statusCode>=300){
					throw new HttpResponseException(
		                    statusCode,
		                    statusLine.getReasonPhrase());
				}
				//如果实体为空，抛出异常
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        // 转化为文本信息, 设置爬取网页的字符集，防止乱码
		        String content = EntityUtils.toString(entity, "UTF-8");
		        //System.out.println(content);
				return new FetchedPage(url,content,statusCode);
			}
		};
		
		//建立连接，并响应
		try{
			fetchedPage=client.execute(getHttp, responseHandler);
		}
		catch(Exception e){
			e.printStackTrace();
			
			//因请求超时等问题产生的异常，将URL放回待抓取队列，重新爬取
			Log.info(">> Put back url: " + url);
			UrlQueue.addFirstElement(url);
		}
		//return FetchedPage(url, content, statusCode);
		return fetchedPage;
	}
	
}
