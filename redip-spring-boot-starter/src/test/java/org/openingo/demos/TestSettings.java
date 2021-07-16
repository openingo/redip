package org.openingo.demos;

import org.junit.Test;
import org.openingo.redip.configuration.RedipConfigurationProperties;
import org.openingo.redip.constants.DictionaryType;
import org.openingo.redip.constants.RemoteDictionaryEtymology;
import org.openingo.redip.dictionary.remote.RemoteDictionary;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.InputStream;
import java.util.Set;

/**
 * TestSettings
 *
 * @author Qicz
 * @since 2021/7/9 10:02
 */
public class TestSettings {

	@Test
	public void loadYml() {
		Yaml yaml = new Yaml(new CustomClassLoaderConstructor(RedipConfigurationProperties.class, TestSettings.class.getClassLoader()));
		InputStream resourceAsStream = TestSettings.class.getClassLoader().getResourceAsStream("ikanalyzer.yml");
		RedipConfigurationProperties map = yaml.loadAs(resourceAsStream, RedipConfigurationProperties.class);
		System.out.println(map);
	}

	private void initRemoteDictionary() {
		Yaml yaml = new Yaml(new CustomClassLoaderConstructor(RedipConfigurationProperties.class, TestSettings.class.getClassLoader()));
		InputStream resourceAsStream = TestSettings.class.getClassLoader().getResourceAsStream("ikanalyzer.yml");
		RedipConfigurationProperties properties = yaml.loadAs(resourceAsStream, RedipConfigurationProperties.class);
		RemoteDictionary.initial(properties);
	}

	@Test
	public void addMySQLWords() {
		this.initRemoteDictionary();
		RemoteDictionary.addWord(RemoteDictionaryEtymology.MYSQL, DictionaryType.MAIN_WORDS, "user", "new words");
		Set<String> userWords = RemoteDictionary.getRemoteWords(RemoteDictionaryEtymology.MYSQL, DictionaryType.MAIN_WORDS, "user");
		System.out.println(userWords);
	}

	@Test
	public void addRedisWords() {
		this.initRemoteDictionary();
		RemoteDictionary.addWord(RemoteDictionaryEtymology.REDIS, DictionaryType.MAIN_WORDS, "user", "new words", "word2", "word3");
		Set<String> userWords = RemoteDictionary.getRemoteWords(RemoteDictionaryEtymology.REDIS, DictionaryType.MAIN_WORDS, "user");
		System.out.println(userWords);
	}
}