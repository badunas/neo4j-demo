package com.badun.neo4jdemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Artsiom Badun.
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        String template = "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"";
        File outFile = new File("/Users/badun/out.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
        for (int i = 1; i < 1000; i++) {
            bufferedWriter.write(String.format(template, i, i, i, i, i, i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
