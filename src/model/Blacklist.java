package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blacklist {
	File phoneFile;
	List<File> blacklistFiles = new ArrayList<>();
	Set<String> phones = new HashSet<>();
	
	public File getPhoneFile() {
		return phoneFile;
	}
	
	public void setPhoneFile(File phoneFile) {
		this.phoneFile = phoneFile;
	}
	
	public List<File> getBlacklistFiles() {
		return blacklistFiles;
	}
	
	public void addBlacklistFiles(File file) {
		blacklistFiles.add(file);
	}
	
	public void removeBlacklistFiles(File file) {
		blacklistFiles.remove(file);
	}
	
	
}
