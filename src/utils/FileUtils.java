package utils;

import java.io.*;

public class FileUtils
{
    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static
    {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory()
    {
        return new File(NOTES_PATH).listFiles();
    }


    public static String fileReader(File file)
    {
        //TODO: Phase1: read content from file
        String content = "";
        try (FileInputStream in = new FileInputStream(file))
        {
            byte[] buffer = new byte[4096];
            int c;
            while ((c = in.read(buffer)) != -1)
            {
                content += new String(buffer);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    public static void fileWriter(String content)
    {
        //TODO: write content on file
        String fileName = getProperFileName(content);
        try (FileOutputStream out = new FileOutputStream(NOTES_PATH + fileName + ".txt"))
        {
            int c = content.length();
            out.write(c);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //TODO: Phase1: define method here for reading file with InputStream
    //TODO: Phase1: define method here for writing file with OutputStream

    //TODO: Phase2: proper methods for handling serialization

    private static String getProperFileName(String content)
    {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}
