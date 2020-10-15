package com.example.news;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.news.controller.NewsController;
import com.example.news.model.NewListInformationXML;
import com.example.news.model.NewsletterNewsItemXML;
import com.example.news.service.NewsService;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(NewsController.class)
public class ControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	NewsService newsService;

	ArrayList<NewListInformationXML> newsList;
	ArrayList<NewListInformationXML> newsList1;
	ArrayList<NewListInformationXML> newsList2;
	NewsletterNewsItemXML london1;
	NewListInformationXML londonNews;
	NewListInformationXML londonNews1;

	@BeforeEach
	public void setUp() {
		londonNews = new NewListInformationXML();
		londonNews.setClubName("London");
		londonNews.setClubWebsiteURL("definitely www.London.com");

		londonNews1 = new NewListInformationXML();
		londonNews1.setClubName("London");
		londonNews1.setClubWebsiteURL("definitely www.London.com");

		london1 = new NewsletterNewsItemXML();
		london1.setArticleURL("www.london.com1");
		london1.setNewsArticleID(1111);
		london1.setPublishDate(LocalDateTime.now().toString());
		london1.setTaxonomies("love this band1");
		london1.setTeaserText("snape might die1");
		london1.setThumbnailImageURL("www.image.com1");
		london1.setTitle("london wins!1");
		london1.setOptaMatchId(1);

		londonNews.add(london1);
		newsList = new ArrayList<NewListInformationXML>();
		newsList1 = new ArrayList<NewListInformationXML>();
		newsList2 = new ArrayList<NewListInformationXML>();

		newsList.add(londonNews);
		newsList1.add(londonNews1);

	}

	@Test
	public void testGetAllNews() throws Exception {
		// checking it passes with data
		Mockito.when(newsService.findAll()).thenReturn(newsList);

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.groupData[0].clubName").value("London")).andDo(print())
				.andExpect(status().isOk());
		
		// checking it passes without data
		Mockito.when(newsService.findAll()).thenReturn(newsList2);

		mockMvc.perform(MockMvcRequestBuilders.get("/"))

				// .andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetNewsByName() throws Exception {
		// checking it passes with with singular team
		Mockito.when(newsService.findByClubName("London")).thenReturn(londonNews);
		Mockito.when(newsService.existsByClubName("London")).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.get("/London"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.teamData.clubName").value("London"))
				.andExpect(status().isOk()).andDo(print());
		
		// checking it passes when team not found
		Mockito.when(newsService.findByClubName("london")).thenReturn(londonNews);
		Mockito.when(newsService.existsByClubName("London")).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.get("/London"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Error")).andExpect(status().isNotFound());

	}

	@Test
	public void testGetSingleNews() throws Exception {
		// checking it passes with single news item
		Mockito.when(newsService.findByNewsletterNewsItemsNewsArticleID("London", 1111)).thenReturn(newsList);

		mockMvc.perform(MockMvcRequestBuilders.get("/London/1111"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success")).andDo(print())
				.andExpect(status().isOk());
		
		// checking it passes when singular news item not found
		Mockito.when(newsService.findByNewsletterNewsItemsNewsArticleID("London", 1111)).thenReturn(newsList1);

		mockMvc.perform(MockMvcRequestBuilders.get("/London/1111"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Error"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("News not found")).andDo(print())
				.andExpect(status().isNotFound());
		
		// checking it passes when team not found
		Mockito.when(newsService.findByNewsletterNewsItemsNewsArticleID("London", 1111)).thenReturn(newsList2);

		mockMvc.perform(MockMvcRequestBuilders.get("/London/1111"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Error")).andDo(print())
				.andExpect(status().isNotFound());

	}
}
