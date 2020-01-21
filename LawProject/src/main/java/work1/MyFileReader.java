package work1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
	private FileReader fr;
	private BufferedReader br;
	/**
	 * MyFileReader 클래스
	 * 기능 -> 파일 읽기 간편제공.
	 * 생성자 = 스트림 생성.
	 * readAll() = 파일 전체 읽어들어온다.
	 * CloseReader() = 스트림을 닫아준다.
	 * @param MyFileReader
	 * @throws FileNotFoundException
	 */
	MyFileReader(String fileName)throws FileNotFoundException{
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		
	}
	public String readAll() throws IOException {
		StringBuffer rtn = new StringBuffer();
		String temp;
		while((temp=br.readLine()) != null)
			rtn.append(temp+"\n");
		return rtn.toString();
	}
	public void closeReader() throws IOException {
		if(fr!=null){
			fr.close(); fr=null;
		}
		if(br!=null){
			br.close(); br=null;
		}
	}
	
	public FileReader getFr() {
		return fr;
	}
	public void setFr(FileReader fr) {
		this.fr = fr;
	}
	public BufferedReader getBr() {
		return br;
	}
	public void setBr(BufferedReader br) {
		this.br = br;
	}
}
