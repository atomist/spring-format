/*
 * Copyright © 2018 Atomist, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

	/**
	 * Format all Java files under the current directory using
	 * spring-format.
	 * @param args takes no args
	 */
	public static void main(String args[]) {
		String path = System.getProperty("user.dir");
		System.out.println("Formatting Java files under " + path);
		try {
			FileFormatter formatter = new FileFormatter();
			try(Stream<Path> s = Files.walk(Paths.get(path))){
				s.filter(Files::isRegularFile)
							.filter(f -> f.getFileName().toString().endsWith("java"))
							.map(f -> formatter.formatFile(f.toFile(), StandardCharsets.UTF_8))
							.filter(FileEdit::hasEdits)
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