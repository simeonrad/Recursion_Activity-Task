package com.company.dsa;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FilesUtils {

    public static void traverseDirectories(String path) {
        traverseDirectories(new File(path), 0);
    }

    private static void traverseDirectories(File directory, int depth) {
        if (directory.isDirectory()) {
            printIndentation(depth);
            System.out.println(directory.getName() + ":");

            // List all files in the directory
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    traverseDirectories(file, depth + 1);
                }
            }
        } else {
            printIndentation(depth);
            System.out.println(directory.getName());
        }
    }

    private static void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
    }

    public static List<String> findFiles(String path, String extension) {
        List<String> result = new ArrayList<>();
        findFiles(new File(path), extension, result);
        return result;
    }

    private static void findFiles(File file, String extension, List<String> result) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    findFiles(subFile, extension, result);
                }
            }
        } else {
            if (file.getName().endsWith("." + extension)) {
                result.add(file.getName());
            }
        }
    }

    public static boolean fileExists(String path, String fileName) {
        return fileExists(new File(path), fileName);
    }

    private static boolean fileExists(File file, String fileName) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    if (fileExists(subFile, fileName)) {
                        return true;
                    }
                }
            }
        } else {
            return file.getName().equals(fileName);
        }
        return false;
    }

    public static Map<String, Integer> getDirectoryStats(String path) {
        Map<String, Integer> stats = new HashMap<>();
        getDirectoryStats(new File(path), stats);
        return stats;
    }

    private static void getDirectoryStats(File file, Map<String, Integer> stats) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    getDirectoryStats(subFile, stats);
                }
            }
        } else {
            // Extract the file extension
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0) {
                String extension = fileName.substring(dotIndex + 1);
                // Update the count for the file extension
                stats.put(extension, stats.getOrDefault(extension, 0) + 1);
            }
        }
    }
}
