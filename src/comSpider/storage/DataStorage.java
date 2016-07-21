package comSpider.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import comSpider.parser.ZhihuQuestions;

public class DataStorage {
	private File dir;
	private File file;
	static volatile boolean isCreatedSucceed=false;
	FileWriter fw;
	public DataStorage(){
		createDirFile();
		try {
			fw=new FileWriter(file,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �洢�������ʵ������ļ�out/questions.txt
	 * @param data
	 * @return
	 */
	public synchronized void store(ZhihuQuestions data){
		// store to DB
		// TODO
		
		
		//store to File
		//�������洢�ı�
		//createDirFile();
		//д���ݵ��ļ���
		try{
			//����̸߳��Կ���һ��FileWriterд��ͬһ���ļ�,�������������
			BufferedWriter writer=new BufferedWriter(fw);//�ڹ���FileWriterʱ���ڶ�����������Ϊtrue
			writer.write(data.toString());				
			writer.flush();
			//writer.close();
			//System.out.println("had stored");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * ����һ��out/questions.txt�ļ�
	 * @param 
	 * @return
	 */
	private void createDirFile(){	
		try{
			//ֻ����һ���ļ�,û���򴴽������򲻴���
			if(!isCreatedSucceed){
				//�����ļ���
				dir=new File("out");
				dir.mkdirs();
				//�ٴ����ļ�
				file=new File("out/questions.txt");//��������·����
				file.createNewFile();
				isCreatedSucceed=true;//�Ѿ������ļ�
				System.out.println("create \"out/question.txt\" file succeed." );
			}
//			else
//				System.out.println("create \"out/question.txt\" file failed." );

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
