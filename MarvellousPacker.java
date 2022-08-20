import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class MarvellousPacker 
{        
    
    FileOutputStream outStream = null;

    String ValidExt[]= {".txt",".c",".java",".cpp"}; // these all files are supported

    public MarvellousPacker(String src, String Dest) throws Exception
    {
        String Magic = "Marvellous11";
        byte arr[]= Magic.getBytes();

        File outfile = new File(Dest); // for writing into the file

        // File infile = null;
        outStream = new FileOutputStream(outfile);
        outStream.write(arr,0,arr.length); //writing header (magic Marvellous11 ) to the output file

        // File folder = new File(src);

        // System.setProperty("user.dir",src);
        
        //  listAllFiles(src);
        FolderAllFile(src);
    }

    public void FolderAllFile(String FolderName) throws IOException
    {
        FileInputStream readerobj = null;

        File dobj = new File(FolderName);

        File allFiles[]= dobj.listFiles();
        String name ;
        int ret =0;

         byte buffer[] = new byte[1024]; // for rading data

        for(int i=0;i<allFiles.length;i++)
        {
            name = allFiles[i].getName();
            if(name.endsWith(".txt") ||name.endsWith(".cpp")||name.endsWith(".java") ||name.endsWith(".c"))
            {
                name = name+" "+(allFiles[i].length());

                for(int j=name.length(); j<100;j++)
                {
                    name=name+" ";
                }

                byte namearray[]= name.getBytes();
                outStream.write(namearray,0,namearray.length);

                readerobj = new FileInputStream(allFiles[i]);

                while((ret= readerobj.read(buffer))!=-1)
                {
                    outStream.write(buffer, 0, ret);
                }

                readerobj.close();
            }
        }

    }
}
    /* 

    public void listAllFiles(String path)
    {
        try
        (Stream< Path > paths = Files.walk(Paths.get(path)))
        {
            paths.forEach(filePath -> 
            {
                if(Files.isRegularFile(filePath))
                {
                    try
                    {
                        String name = filePath.getFileName().toString();
                        String ext = name.substring(name.lastIndexOf("."));

                        List<String>list = Arrays.asList(ValidExt);

                        if(list.contains(ext))
                        {
                            File file = new File(filePath.getFileName().toString());

                            Pack(file.getAbsolutePath());
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            });
        }
        catch (IOException e)
        {
            System.out.println("problems");
            System.out.println(e);
        }
        
    }

    public void Pack(String filePath)
    {
        FileInputStream instream = null;

        try
        {
           
            byte buffer[] = new byte[1024]; // for rading data
            
            int length=0;

            byte temp[] = new byte[100]; // for reading filename and size
            
            File fobj = new File(filePath);
            
            System.out.println(" length of file"+fobj.length() );
            System.out.println("file name "+fobj.getName().length());
            String Header = filePath+" "+fobj.length();
            
            System.out.println(Header);
            System.out.println("Hello");
            for(int i= Header.length(); i<100; i++)
            {
                Header+= " ";
            }

            temp = Header.getBytes();

            outStream.write(temp,0,temp.length);

            
            instream = new FileInputStream(filePath);
            
            while((length = instream.read(buffer))>0)
            {
                outStream.write(buffer, 0, length);
            }
            instream.close();  // for reading next file data first file should be close
                                // dont't close the outstream b'cause we have to write data of next file (append)
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("probmes");
        }
    }
}

*/