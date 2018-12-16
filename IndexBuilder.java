package io.bittiger.ads;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;

public class IndexBuilder {
	private int EXP = 0; //0: never expire
	private String mMemcachedServer;
	private int mMemcachedPortal;
	private String mysql_host;
	private String mysql_db;
	private String mysql_user;
	private String mysql_pass;
	private MySQLAccess mysql;
	private MemcachedClient cache;


	public void Close() {
		if(mysql != null) {
			try {
				mysql.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public IndexBuilder(String memcachedServer,int memcachedPortal,String mysqlHost,String mysqlDb,String user,String pass)
	{
		mMemcachedServer = memcachedServer;
		mMemcachedPortal = memcachedPortal;
		mysql_host = mysqlHost;
		mysql_db = mysqlDb;
		mysql_user = user;
		mysql_pass = pass;
		mysql = new MySQLAccess(mysql_host, mysql_db, mysql_user, mysql_pass);
		String address = mMemcachedServer + ":" + mMemcachedPortal;
		try
		{
			cache = new MemcachedClient(new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(), AddrUtil.getAddresses(address));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Boolean buildInvertIndex(Ad ad)
	{
		//follow up, how to change it to zone index
		//1. key: zone_id + token, for example: z1 +"nike", z1: keyword,  z2 +"nike", z2 : detail_url..
		String keyWords = Utility.strJoin(ad.keyWords, ",");
		List<String> tokens = Utility.cleanedTokenize(keyWords);

		//zone: keywords
		for(int i = 0; i < tokens.size();i++)
		{
			String key = tokens.get(i);
			if(cache.get(key) instanceof Set)
			{
				@SuppressWarnings("unchecked")
				Set<Long>  adIdList = (Set<Long>)cache.get(key);
				//hide logic: how to handle multiple adId, DEDUPE is done by Set<Long>
				adIdList.add(ad.adId);
				//refresh post  list
			    cache.set(key, EXP, adIdList);
			}
			else
			{
				Set<Long>  adIdList = new HashSet<Long>();
				adIdList.add(ad.adId);
				cache.set(key, EXP, adIdList);
			}
		}

		//zone: detail url

		//zone: description
		return true;
	}

	//add position to posting list
	public Boolean buildInvertIndexV2(Ad ad)
	{
		String keyWords = Utility.strJoin(ad.keyWords, ",");
		List<String> tokens = Utility.cleanedTokenize(keyWords);
		for(int i = 0; i < tokens.size();i++)
		{
			String key = tokens.get(i);
			if(cache.get(key) instanceof HashMap)
			{
				@SuppressWarnings("unchecked")
				HashMap<Long, Set<Integer>>  adIdList = (HashMap<Long, Set<Integer>>)cache.get(key);
				if(adIdList.containsKey(ad.adId)) {
					adIdList.get(ad.adId).add(i);
				} else {
					Set<Integer> positions = new HashSet<Integer>();
					positions.add(i);
					adIdList.put(ad.adId, positions);
				}
			    cache.set(key, EXP, adIdList);
			}
			else
			{
				//key: document Id, value: sorted position list
				HashMap<Long, Set<Integer>>  adIdList = new HashMap<Long, Set<Integer>>();
				Set<Integer> positions = new HashSet<Integer>();
				positions.add(i);
				adIdList.put(ad.adId, positions);
				cache.set(key, EXP, adIdList);
			}
		}
		return true;
	}

	//follow up
	//1. add document frequency to inverted index => no need to change data structure, DF = HashMap.size()
	//2. sort posting list by term-doc relevance(importance) score: number of times term show in doc / total number of terms in doc
	//solution1: create one more HashMap<key: docId, val: total number of terms in doc (token size)>
	//solution2: HashMap<Long, Set<Integer>> ==> class DocInfo {Set<Integer> positionList; int totalNumTerms}, HashMap<Long, DocInfo>




	public Boolean buildForwardIndex(Ad ad)
	{
		try
		{
			mysql.addAdData(ad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public Boolean updateBudget(Campaign camp)
	{
		try
		{
			mysql.addCampaignData(camp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
