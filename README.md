# Zipf-s-Law-and-Heap-s-Law-Chart
네이버 뉴스 기사를 10,000개를 수집한 후, 토큰을 분리하여, 지프의 법칩과 힙스의 법칙을 그래프로 그리는 소스이다.

## 1. 수집 방법  
JSoup 라이브러리를 사용하였고, seedURL은 네이버 뉴스이다.  
seedURL : https://news.naver.com/main/ranking/popularDay.nhn?rankingType=popular_day&sectionId=103         

seedURL은 크롤링의 시작 대상인 URL이며, GET 형식 변수로 sectionId와 date가 존재한다.  
- sectionId = 분야를 의미하며 텍스트 기사는 100~105가 있다.(정치, 경제, 사회, 생활/문화, 세계, IT/과학)  
- date = 한 분야에서 지정한 날짜의 인기있는 30개의 기사목록을 얻을 수 있다.  
  
네이버 뉴스의 GET 형식 변수 sectionId와 date 값을 변경하며, 문서를 10,000개의 데이터를 수집하였다.
   
## 2. 그래프를 그리는 과정
문서 로드 -> 토큰 분리 -> 중복 제거 및 단어 빈도 체크 -> 랭킹화 -> 그래프 출력 과정으로 이루어진다.
  
## 3. 결과
<div>
<img width="700" src="https://user-images.githubusercontent.com/60133320/72909078-cd203a80-3d79-11ea-9f62-d30210462e6f.png">
</div>
  
# 설치 방법
cloneURL : https://github.com/dongdang-cho/Zipf-s-Law-and-Heap-s-Law-Chart.git
<br>ZIP 파일을 다운 받아, 이클립스에 프로젝트를 import 하거나, cloneURL을 이용하여 git import 기능을 사용한다.

# 사용 예제
JSoup의 속도는 빠르지만, 많은 문서를 수집하기 위해서는 많은 시간이 소요되기 때문에, 예제는 2가지로 나누어 설명할 수 있다.

1. JSoup을 사용한 뉴스기사 파싱.
  - JsoupParser.java 파일의 main 메소드가 시작점이다.
<pre>
<code>

    JsoupParser jp = new JsoupParser(1000); //1000개 문서를 수집함.
    jp.parsing(); // 설정된 seedURL부터 크롤링을 시작하여, document 폴더에 저장함.
    
</code>
</pre>

2. 수집된 문서를 가공하여 zipf's Law과 Heap's Law 그래프 출력.
  - MyApp.java 파일의 main 메소드가 시작점이다.
<pre>
<code>
// document폴더에 있는 텍스트 문서를 로드하여, HashMap에 저장함.
DocumentManager dMgr = new DocumentManager("document");

//정규표현식을 이용해서 토큰링하여, MyTokenizer.List에 저장함.
MyTokenizer tokenizer = new MyTokenizer("[\\s\\p{Punct}]+"); 
for(String key : dMgr.getContentMap().keySet()) {
	String value = dMgr.getContentMap().get(key);
tokenizer.tokenizing(value);
}

//토큰 분리된 List에 빈도수를 체크하고, 랭킹화하여, 그래프로 출력한다.
  Statistician statistician = new Statistician();
  statistician.frequencyCheck(tokenizer.getOutputList());
  statistician.rankTerms(); 
  statistician.showZipfChart();
  statistician.showHeapsChart();
      
</code>
</pre>
# 개발 환경 설정 방법

프로젝트는 Maven Project로 구성되어 있으며, JSoup과 JFreeChart 라이브러리를 사용하고 있다.
pom.xml을 추가해두었지만, 참고바란다.

	<dependencies>
	<dependency>
	    <groupId>org.jfree</groupId>
	    <artifactId>jfreechart</artifactId>
	    <version>1.0.19</version>
	</dependency>
	<dependency> 
	<groupId>org.jsoup</groupId>
	 <artifactId>jsoup</artifactId> 
	 <version>1.12.1</version> 
	</dependency>
	</dependencies>

# 라이센스
   GNU LESSER GENERAL PUBLIC LICENSE version 3

