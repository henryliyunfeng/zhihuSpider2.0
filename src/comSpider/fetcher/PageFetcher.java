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
	 * ����HttpClientʵ��������ʼ�����Ӳ���
	 */
	private static CloseableHttpClient client=HttpClients.createDefault();
	/**
	 * �����ر�HttpClient����
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
	 * ����url��ȡ��ҳ����
	 * @param url
	 * @return FetchedPage
	 */
	public  static FetchedPage getContentFromUrl(String url){
		FetchedPage fetchedPage=null;
		
		// ����Get���󣬲�����Header
		HttpGet getHttp = new HttpGet(url);	
		//System.out.println(getHttp.getRequestLine());
		
		//������Ӧ������ResponseHandler,һ��Ҫ��дhandleResponse(HttpResponse response)����
		ResponseHandler<FetchedPage> responseHandler = new ResponseHandler<FetchedPage>(){

			@Override
			public FetchedPage handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				// TODO Auto-generated method stub
				StatusLine statusLine=response.getStatusLine();//��Ӧ��״̬��
				int statusCode=statusLine.getStatusCode();//״̬��
				//System.out.println(statusCode);
				HttpEntity entity=response.getEntity();//��Ӧ��ʵ��
				//���״̬��>=300,�׳���Ӧ�쳣
				if(statusCode>=300){
					throw new HttpResponseException(
		                    statusCode,
		                    statusLine.getReasonPhrase());
				}
				//���ʵ��Ϊ�գ��׳��쳣
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        // ת��Ϊ�ı���Ϣ, ������ȡ��ҳ���ַ�������ֹ����
		        String content = EntityUtils.toString(entity, "UTF-8");
		        //System.out.println(content);
				return new FetchedPage(url,content,statusCode);
			}
		};
		
		//�������ӣ�����Ӧ
		try{
			fetchedPage=client.execute(getHttp, responseHandler);
		}
		catch(Exception e){
			e.printStackTrace();
			
			//������ʱ������������쳣����URL�Żش�ץȡ���У�������ȡ
			Log.info(">> Put back url: " + url);
			UrlQueue.addFirstElement(url);
		}
		//return FetchedPage(url, content, statusCode);
		return fetchedPage;
	}
	
}
