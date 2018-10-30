
package com.atomist;

import java.io.Files;
import java.nio.file.Paths;

import io.spring.javaformat.formatter.FileEdit;
import io.spring.javaformat.formatter.FileFormatter;
import io.spring.javaformat.formatter.FileFormatterException;

public class Formatter {

    public static void main(String args[]) {
        String path = args[0];
        try {
            FileFormatter formatter = new FileFormatter();
            Files.walk(Paths.get(path))
                .filter(f -> Files.isRegularFile())
                .filter(f -> f.getName().endsWith(".java"))
                .forEach(f -> formatter.formatFile(f, "UTF-8"))
                .forEach(Formatter::save);
        }
        catch (FileFormatterException ex) {
            throw new Exception("Unable to format file " + ex.getFile(), ex);
        }
    }

    private static void save(FileEdit edit) {
		System.out.println("Formatting file " + edit.getFile());
		//edit.save();
	}
    
}