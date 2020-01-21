package work1;

import java.io.IOException;


public class MyApp {
	
	public static void main(String[] args) throws IOException {
		/*
		 * 순서
		 *	사전에 JsoupPaser를 통해 크롤릴을 수행하여야 함!
		 * 
		 * 1. Crawling한 문서들을 읽는다.(문서를 읽어서 관리할 자료구조 및 클래스 필요) -> DocumentManager
		 * 2. 파싱한 문서들을 토큰분리한다. -> MyTokenizer
		 * 3. 빈도수를 체크하여 지프, 힙스의 법칙을 증명한다. -> Statistician
		 */
		
		// 1. 문서를 읽고, 제목과 내용을 HashMap에 저장한다.
		DocumentManager dMgr = new DocumentManager("document");
		
		// 2. 정규표현식을 통해  토큰분리를 실행한다. 결과는 ArrayList에 저장한다.
		MyTokenizer tokenizer = new MyTokenizer("[\\s\\p{Punct}]+");
		for(String key : dMgr.getContentMap().keySet()) {
			String value = dMgr.getContentMap().get(key);
            tokenizer.tokenizing(value);
		}
		
		/*
		 * 3. 분리된 토큰을 중복을 제거함과 동시에 빈도수를 세어 HashMap에 저장한다.
		 *    순서를 가진 자료구조 ArrayList에 내림차순으로 정렬하여 저장한다.
		 *    zipf 그래프, Heaps 그래프를 그린다. 
		 */
		
		  Statistician statistician = new Statistician();
		  statistician.frequencyCheck(tokenizer.getOutputList());
		  statistician.rankTerms(); 
		  statistician.showZipfChart();
		  statistician.showHeapsChart();
	}
}
