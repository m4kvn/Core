package com.nepian.core.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.nepian.core.utils.exception.SaveYamlConfigurationException;

public class FileUtil {
	
	/**
	 * ファイルを読み込む
	 * @param folder
	 * @param fileName
	 * @return File
	 */
	public static File loadFile(File folder, String fileName) {
		return load(folder, fileName, FileType.FILE);
	}
	
	/**
	 * フォルダを読み込む
	 * @param folder
	 * @param fileName
	 * @return File
	 */
	public static File loadFolder(File folder, String fileName) {
		return load(folder, fileName, FileType.FOLDER);
	}
	
	/**
	 * ロードするファイルの種類
	 * @author codepro2014MBA02
	 *
	 */
	public enum FileType { FILE, FOLDER }
	
	/**
	 * ファイルかフォルダをロードする
	 * @param folder
	 * @param fileName
	 * @param type ロードする種類をFileTypeから選択する
	 * @return File
	 */
	public static File load(File folder, String fileName, FileType type) {
		File file = new File(folder, fileName);
		if (!file.exists()) {
			try {
				if (file.getParent() != null) {
					file.getParentFile().mkdirs();
				}

				switch (type) {
				case FILE:
					file.createNewFile();
					break;
				case FOLDER:
					file.mkdir();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
	 * YamlConfiguration を取得する
	 * @param file
	 * @return YamlConfiguration
	 */
	public static YamlConfiguration getYml(File file) {
		return YamlConfiguration.loadConfiguration(file);
	}
	
	/**
	 * YamlConfiguration を file に保存する
	 * @param file
	 * @param yml
	 * @throws SaveYamlConfigurationException 
	 */
	public static void saveYml(File file, YamlConfiguration yml)
			throws SaveYamlConfigurationException {
		try {
			yml.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SaveYamlConfigurationException(file);
		}
	}
}
