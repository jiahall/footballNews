package com.example.news;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.news.model.NewListInformationXML;
import com.example.news.service.NewsService;

@SpringBootApplication
public class NewsApplication {

	@Autowired
	private NewsService newsService;

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

	// This method would be used in a scheduling class to pull data from website
	// to the news Database via an api call at a certain time

	public void insertData() throws MalformedURLException, JAXBException {
		URL url = new URL("https://www.???.com/api/???/getnewlistinformation?count=60");
		JAXBContext jaxbContext1 = JAXBContext.newInstance(NewListInformationXML.class);

		//unmarshalls data to java object
		Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
		NewListInformationXML FootballNews = (NewListInformationXML) jaxbUnmarshaller.unmarshal(url);

		newsService.insertNews(FootballNews.getNewsletterNewsItems(), FootballNews.getClubName());
	}

}
