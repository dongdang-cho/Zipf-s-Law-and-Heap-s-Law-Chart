package work1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManager {
	private File path;
	private List<File> fileList;
	private Map<String,String> contentMap;
	/*
	 * MyFileReader에 의존성이 있음.
	 * 문서 관리 클래스
	 * 1. 지정한 경로 안에 있는 모든 파일들을 불러와서 fileList에 담는다.
	 * 2. fileList대로  내용을 읽어 파일이름,내용물로 contentMap에 담는다.
	 * 3. getter 메소드를 통해 사용.
	 */
	public DocumentManager(String pathName) throws IOException {
		this.path = new File(pathName); 
		fileList = new ArrayList<File>(Arrays.asList(this.path.listFiles()));
		contentMap = new HashMap<String,String>();
		readFiles();
	}
	
	private void readFiles() throws IOException {
		MyFileReader reader;
		for(File file : fileList) {
			reader = new MyFileReader(file.getPath());
			String content = reader.readAll();
			contentMap.put(file.getName(),content);
			reader.closeReader();
		}
	}

	public List<File> getFileList() {
		return fileList;
	}

	public Map<String, String> getContentMap() {
		return contentMap;
	}

	
}
