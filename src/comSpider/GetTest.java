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
	        //Ŀ��ҳ��  
	        String url = "https://www.zhihu.com/explore";
	        // ����Get����
			HttpGet getHttp = new HttpGet(url);	
			System.out.println(getHttp.getRequestLine()); 
			//2016-07-17������Ӧ������ResponseHandler,һ��Ҫ��дhandleResponse(HttpResponse response)����
			ResponseHandler<String> responseHandler = new ResponseHandler<String>(){

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
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
