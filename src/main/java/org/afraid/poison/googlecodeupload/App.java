package org.afraid.poison.googlecodeupload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import org.afraid.poison.common.CollectionUtil;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class App {

	public static void main(String[] args) throws MalformedURLException, FileNotFoundException, IOException {
		try {
			CommandLineParser parser=new PosixParser();
			Options options=buildOptions();
			CommandLine line=parser.parse(options, args);
			if (line.getArgList().isEmpty()) {
				printHelp();
				System.exit(0);
			}
			Project project=new Project();
			project.setUserName(line.getOptionValue("u"));
			project.setPassword(line.getOptionValue("p"));
			project.setName(line.getOptionValue("n"));
			FileDefinition fileDefinition=new FileDefinition();
			fileDefinition.setFile(new File((String) line.getArgList().get(0)));
			if (line.hasOption("s")) {
				fileDefinition.setSummary(line.getOptionValue("s"));
			}
			if (line.hasOption("l") && null!=line.getOptionValue("l")) {
				fileDefinition.setLabels(new HashSet<String>(CollectionUtil.fromArray(line.getOptionValue("l").split(","))));
			}
			project.upload(fileDefinition);
		} catch (ParseException ex) {
			System.err.println(ex);
			printHelp();
		}
	}

	public static Options buildOptions() {
		Option user=new Option("u", "user", true, "Your Google Code username");
		Option password=new Option("p", "password", true, "Your Google Code password");
		Option projectName=new Option("n", "project", true, "Google Code project name");
		Option summary=new Option("s", "summary", true, "Short description of the file");
		Option labels=new Option("l", "labels", true, "An optional list of comma-separated labels to attach to the file");

		Options options=new Options();
		options.addOption(user);
		options.addOption(password);
		options.addOption(projectName);
		options.addOption(summary);
		options.addOption(labels);
		return options;
	}

	public static void printHelp() {
		HelpFormatter formatter=new HelpFormatter();
		formatter.printHelp("java -jar googlecode-upload.jar [OPTION]... FILE", buildOptions());
	}
}
