package com.example.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.news.model.NewListInformationXML;

@Repository
public interface NewsRepository extends MongoRepository<NewListInformationXML, String>, MongoTemplateRepository {

	public List<NewListInformationXML> findAll();

	public void deleteByClubName(String clubName);

	@Query("{ 'clubWebsiteURL' : ?0 }")
	public NewListInformationXML queryTest(String website);

	public NewListInformationXML findByClubName(String clubName);

	public Boolean existsByClubName(String clubName);

}
