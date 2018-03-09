package com.freeuni.oop.seminar3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * File reading example.
 */
public class MyFileReader {

    // Classic text file reading code -- the standard while/readLine loop // in a try/catch.
    public static void echo(String filename) {
        try {
            // Create reader for the given filename
            BufferedReader in = new BufferedReader(new FileReader(filename));

            // While/break to call readLine() until it returns null
            while (true) {
                // the while/readLine logic can be written more compactly as // "while ((line=in.readLine()) != null) {"
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                // do something with line
                System.out.println(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException except) {
            // The code above jumps to here on an IOException,
            // otherwise this code does not run.
            // Good simple strategy: print safestack trace, maybe exit except.printStackTrace();
            // System.exit(1); // could do this too
            except.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyFileReader.echo("InsertYourFileLocation.txt");
    }

}
