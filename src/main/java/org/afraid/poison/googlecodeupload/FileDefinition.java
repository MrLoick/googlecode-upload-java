package org.afraid.poison.googlecodeupload;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import org.afraid.poison.common.CollectionUtil;

/**
 *
 * @author Andreas Schnaiter <rc.poison@gmail.com>
 */
@XmlRootElement
public class FileDefinition {

	private File file;
	private String targetFileName;
	private String summary;
	private Set<String> labels;

	public FileDefinition() {
	}

	public FileDefinition(File file, String summary) {
		this.file=file;
		this.summary=summary;
	}

	public FileDefinition(File file, String targetFileName, String summary, Set<String> labels) {
		this.file=file;
		this.targetFileName=targetFileName;
		this.summary=summary;
		this.labels=labels;
	}
	
	public FileDefinition(File file, String targetFileName, String summary, String[] labels) {
		this.file=file;
		this.targetFileName=targetFileName;
		this.summary=summary;
		this.labels=new HashSet<String>(CollectionUtil.fromArray(labels));
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file=file;
	}

	public Set<String> getLabels() {
		return labels;
	}

	public void setLabels(Set<String> labels) {
		this.labels=labels;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary=summary;
	}

	public String getTargetFileName() {
		if (null==targetFileName) {
			return getFile().getName();
		}
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName=targetFileName;
	}
}