
package com.atomist;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.charset.StandardCharsets;

import io.spring.javaformat.formatter.FileEdit;
import io.spring.javaformat.formatter.FileFormatter;
import io.spring.javaformat.formatter.FileFormatterException;

public class Formatter {

	public static void main(String args[]) throws Exception {
		String path = System.getProperty("user.dir");
		System.out.println("Formatting Java files under " + path);
		try {
			FileFormatter formatter = new FileFormatter();
			Files.walk(Paths.get(path)).filter(Files::isRegularFile)
					.filter(f -> f.getFileName().toString().endsWith("java"))
					.map(f -> formatter.formatFile(f.toFile(), StandardCharsets.UTF_8))
					.forEach(Formatter::save);
		}
		catch (FileFormatterException ex) {
			throw new Exception("Unable to format file " + ex.getFile(), ex);
		}
	}

	private static void save(FileEdit edit) {
		System.out.println("Formatting file " + edit.getFile());
		edit.save();
	}

}