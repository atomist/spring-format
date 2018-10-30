
package com.atomist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import io.spring.javaformat.formatter.FileEdit;
import io.spring.javaformat.formatter.FileFormatter;
import io.spring.javaformat.formatter.FileFormatterException;

public class Formatter {
	public static void main(String args[]) {
		String path = System.getProperty("user.dir");
		System.out.println("Formatting Java files under " + path);
		try {
			FileFormatter formatter = new FileFormatter();
			try(Stream<Path> s = Files.walk(Paths.get(path))){
				s.filter(Files::isRegularFile)
							.filter(f -> f.getFileName().toString().endsWith("java"))
							.map(f -> formatter.formatFile(f.toFile(), StandardCharsets.UTF_8))
							.forEach(Formatter::save);
			}
		} catch (FileFormatterException ex) {
			throw new RuntimeException("Unable to format file " + ex.getFile(), ex);
		} catch (IOException ioEx) {
			throw new RuntimeException("Filesystem error", ioEx);
		}
	}

	private static void save(FileEdit edit) {
		System.out.println("Formatting file " + edit.getFile());
		edit.save();
	}

}