package com.example.news;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalDateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.example.news.model.NewListInformationXML;
import com.example.news.model.NewsletterNewsItemXML;

import com.example.news.repository.NewsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTests {

	@Autowired
	NewsRepository repository;
	
	

	@Before
	public void init() {
		repository.deleteAll();

		NewListInformationXML londonNews = new NewListInformationXML();
		londonNews.setClubName("Brighton");
		londonNews.setClubWebsiteURL("definitely www.Brighton.com");

		NewsletterNewsItemXML london1 = new NewsletterNewsItemXML();
		london1.setArticleURL("www.london.com1");
		london1.setNewsArticleID(1111);
		london1.setPublishDate(LocalDateTime.now().toString());
		london1.setTaxonomies("love this band1");
		london1.setTeaserText("snape might die1");
		london1.setThumbnailImageURL("www.image.com1");
		london1.setTitle("london wins!1");
		london1.setOptaMatchId(1);

		NewsletterNewsItemXML london2 = new NewsletterNewsItemXML();
		london2.setArticleURL("www.london.com2");
		london2.setNewsArticleID(2);
		london2.setPublishDate(LocalDateTime.now().toString());
		london2.setTaxonomies("love this band2");
		london2.setTeaserText("snape might die2");
		london2.setThumbnailImageURL("www.image.com2");
		london2.setTitle("london wins!2");
		london2.setOptaMatchId(2);

		NewsletterNewsItemXML london3 = new NewsletterNewsItemXML();
		london3.setArticleURL("www.london.com3");
		london3.setNewsArticleID(3);
		london3.setPublishDate(LocalDateTime.now().toString());
		london3.setTaxonomies("love this band3");
		london3.setTeaserText("snape might die3");
		london3.setThumbnailImageURL("www.image.com3");
		london3.setTitle("london wins!3");
		london3.setOptaMatchId(3);

		londonNews.add(london1);
		londonNews.add(london2);
		londonNews.add(london3);

		NewListInformationXML pitseaNews = new NewListInformationXML();
		pitseaNews.setClubName("Pitsea");
		pitseaNews.setClubWebsiteURL("definitely www.pitsea.com");

		NewsletterNewsItemXML pitsea = new NewsletterNewsItemXML();
		pitsea.setArticleURL("www.pitsea.com");
		pitsea.setNewsArticleID(1);
		pitsea.setPublishDate(LocalDateTime.now().toString());
		pitsea.setTaxonomies("love this band");
		pitsea.setTeaserText("snape might die");
		pitsea.setThumbnailImageURL("www.image.com");
		pitsea.setTitle("pitsea wins!");
		pitsea.setOptaMatchId(1234);

		pitseaNews.add(pitsea);
		repository.save(pitseaNews);

		NewListInformationXML brightonNews = new NewListInformationXML();
		brightonNews.setClubName("Brighton");
		brightonNews.setClubWebsiteURL("definitely www.Brighton.com");

		NewsletterNewsItemXML brighton1 = new NewsletterNewsItemXML();
		brighton1.setArticleURL("www.brighton.com1");
		brighton1.setNewsArticleID(1111);
		brighton1.setPublishDate(LocalDateTime.now().toString());
		brighton1.setTaxonomies("love this band1");
		brighton1.setTeaserText("snape might die1");
		brighton1.setThumbnailImageURL("www.image.com1");
		brighton1.setTitle("brighton wins!1");
		brighton1.setOptaMatchId(1);

		NewsletterNewsItemXML brighton2 = new NewsletterNewsItemXML();
		brighton2.setArticleURL("www.brighton.com2");
		brighton2.setNewsArticleID(2);
		brighton2.setPublishDate(LocalDateTime.now().toString());
		brighton2.setTaxonomies("love this band2");
		brighton2.setTeaserText("snape might die2");
		brighton2.setThumbnailImageURL("www.image.com2");
		brighton2.setTitle("brighton wins!2");
		brighton2.setOptaMatchId(2);

		NewsletterNewsItemXML brighton3 = new NewsletterNewsItemXML();
		brighton3.setArticleURL("www.brighton.com3");
		brighton3.setNewsArticleID(3);
		brighton3.setPublishDate(LocalDateTime.now().toString());
		brighton3.setTaxonomies("love this band3");
		brighton3.setTeaserText("snape might die3");
		brighton3.setThumbnailImageURL("www.image.com3");
		brighton3.setTitle("brighton wins!3");
		brighton3.setOptaMatchId(3);

		brightonNews.add(brighton1);
		brightonNews.add(brighton2);
		brightonNews.add(brighton3);

		repository.save(brightonNews);

		NewsletterNewsItemXML test = new NewsletterNewsItemXML();
		test.setArticleURL("www.worked.com");
		test.setNewsArticleID(123456);
		test.setPublishDate(LocalDateTime.now().toString());
		test.setTaxonomies("band");
		test.setTeaserText("ooh did it work");
		test.setThumbnailImageURL("thumb.gif");
		test.setTitle("worked");
		test.setOptaMatchId(12345);

	}

	@Test
	public void countAllNews() {
		assertEquals(2, repository.findAll().size());
	}

	@Test
	public void checkExists() {
		assertEquals(false, repository.existsByClubName("england"));
		assertEquals(true, repository.existsByClubName("Brighton"));
	}

	@Test
	public void checkRemoved() {
		repository.deleteByClubName("pitsea");
		Assertions.assertNull(repository.findByClubName("pitsea"));
	}

	@Test
	public void checkClub() {
		Assertions.assertEquals("Brighton", repository.findByClubName("Brighton").getClubName());
		Assertions.assertEquals(3, repository.findByClubName("Brighton").getNewsletterNewsItems().size());
	}

	@Test
	public void checkSingleNews() {
		Assertions.assertNotNull(repository.findByNewsletterNewsItemsNewsArticleID("Brighton", 1111));
		Assertions.assertEquals("brighton wins!3", repository.findByNewsletterNewsItemsNewsArticleID("Brighton", 3)
				.get(0).getNewsletterNewsItems().get(0).getTitle());
		Assertions.assertEquals(0, repository.findByNewsletterNewsItemsNewsArticleID("Brighon", 3).size());
		Assertions.assertEquals("pitsea wins!", repository.findByNewsletterNewsItemsNewsArticleID("Pitsea", 1).get(0)
				.getNewsletterNewsItems().get(0).getTitle());
		Assertions.assertEquals("definitely www.pitsea.com",
				repository.findByNewsletterNewsItemsNewsArticleID("Pitsea", 1).get(0).getClubWebsiteURL());
	}

	@Test
	public void testCapitalize() {
		String clubName = "brighton";

		clubName = StringUtils.capitalize(clubName);
		Assertions.assertEquals("Brighton", StringUtils.capitalize(clubName));

	}

	@Test
	public void checkInsert() {

		NewListInformationXML brightonNews = new NewListInformationXML();
		brightonNews.setClubName("Brighton");
		brightonNews.setClubWebsiteURL("definitely www.Brighton.com");

		NewsletterNewsItemXML brighton4 = new NewsletterNewsItemXML();
		brighton4.setArticleURL("www.brighton.com4");
		brighton4.setNewsArticleID(4444);
		brighton4.setPublishDate(LocalDateTime.now().toString());
		brighton4.setTaxonomies("love this band4");
		brighton4.setTeaserText("snape might die4");
		brighton4.setThumbnailImageURL("www.image.com4");
		brighton4.setTitle("brighton wins!4");
		brighton4.setOptaMatchId(4);

		NewsletterNewsItemXML brighton5 = new NewsletterNewsItemXML();
		brighton5.setArticleURL("www.brighton.com5");
		brighton5.setNewsArticleID(5);
		brighton5.setPublishDate(LocalDateTime.now().toString());
		brighton5.setTaxonomies("love this band5");
		brighton5.setTeaserText("snape might die5");
		brighton5.setThumbnailImageURL("www.image.com5");
		brighton5.setTitle("brighton wins!5");
		brighton5.setOptaMatchId(2);

		NewsletterNewsItemXML brighton3 = new NewsletterNewsItemXML();
		brighton3.setArticleURL("www.brighton.com3");
		brighton3.setNewsArticleID(3);
		brighton3.setPublishDate(LocalDateTime.now().toString());
		brighton3.setTaxonomies("love this band3");
		brighton3.setTeaserText("snape might die3");
		brighton3.setThumbnailImageURL("www.image.com3");
		brighton3.setTitle("brighton wins!3");
		brighton3.setOptaMatchId(3);

		brightonNews.add(brighton4);
		brightonNews.add(brighton5);
		brightonNews.add(brighton3);

		repository.insertNews(brightonNews.getNewsletterNewsItems(), brightonNews.getClubName());

		Assertions.assertEquals(5, repository.findByClubName("Brighton").getNewsletterNewsItems().size());
		Assertions.assertEquals(5,
				repository.findByClubName("Brighton").getNewsletterNewsItems().get(4).getNewsArticleID());

	}

	@Test
	public void checkUnmarshallData() {
		NewListInformationXML list = null;
		try {

			File file = new File("pitsea.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(NewListInformationXML.class);

			/**
			 * the only difference with the marshaling operation is here
			 */
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			list = (NewListInformationXML) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		Assertions.assertNotNull(list.getClubName());
		Assertions.assertEquals(1, list.getNewsletterNewsItems().get(0).getNewsArticleID());
	}
}
