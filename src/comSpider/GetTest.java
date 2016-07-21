package comSpider;

import java.io.IOException;

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


public class GetTest {
	private static CloseableHttpClient client=HttpClients.createDefault();
	  public static void main(String[] args) throws Exception {  
	        //目标页面  
	        String url = "https://www.zhihu.com/explore";
	        // 创建Get请求
			HttpGet getHttp = new HttpGet(url);	
			System.out.println(getHttp.getRequestLine()); 
			//2016-07-17创建响应处理器ResponseHandler,一定要重写handleResponse(HttpResponse response)方法
			ResponseHandler<String> responseHandler = new ResponseHandler<String>(){

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
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
					//return new FetchedPage(url,content,statusCode);
			        return content;
				}
			};
			
			try{
				System.out.println(client.execute(getHttp));
			}catch(Exception e){
				e.printStackTrace();
			}
	    }  

}
