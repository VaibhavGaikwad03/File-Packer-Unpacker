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

public class MarvellousUnpack 
{
    FileOutputStream outstream = null;

    public MarvellousUnpack(String src) throws Exception
    {
        unpack(src);
    }

    public void unpack(String PackFileName) throws Exception
    {
        try
        {
            FileInputStream fiobj = new FileInputStream(PackFileName);

            byte Magic[] = new byte[12];
            fiobj.read(Magic, 0, Magic.length); // for reading first 12 Bytes

            String Magicstr = new String(Magic);

            if (!Magicstr.equals("Marvellous11")) 
            {
                throw new InvalidFileException("Invalid Packed file format ");
            }

            byte headerarray[] = new byte[100];
            int length = 0;

            while((length = fiobj.read(headerarray,0, 100))>0)
            {
                String headString = new String(headerarray);

                String token[]= headString.split(" ");

                File fobjnew = new File(token[0]);

                int size = Integer.parseInt(token[1]);

                byte dataArray[]= new byte [size];

                fiobj.read(dataArray,0,size);
                FileOutputStream foobj = new FileOutputStream(fobjnew);

                foobj.write(dataArray,0,size);

            }

        }
        catch (InvalidFileException obj) 
        {
            throw new InvalidFileException("Invalid pcked file format ");
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
/* 
    public void unpack(String filePath) throws Exception
    {
        try
        {
            FileInputStream instream = new FileInputStream(filePath);

            byte header[]= new byte[100];
            int length=0;

            byte Magic[]= new byte[12];
            instream.read(Magic,0,Magic.length);

            String Magicstr = new String(Magic);

            if(!Magicstr.equals("Marvellous11"))
            {
                throw new InvalidFileException("Invalid Packed file format ");
            }

            while((length = instream.read(header,0,100))>0) // (filename+ " "+size+.......)
            {
                String str = new String(header); // byte array header to string
                String ext = str.substring(str.lastIndexOf("/"));
                ext= ext.substring(1);

                String words[] = ext.split("\\s");

                String filename = words[0];

                int size = Integer.parseInt(words[1]);

                byte arr[]= new byte[size]; // in arrr we are reading data after 100 bytes read complete ie,(after filename+ " "+size+.......)

                instream.read(arr,0,size);
                FileOutputStream fout = new FileOutputStream(filename);

                fout.write(arr,0,size); // writing data in ouput file

            }
        }
        catch(InvalidFileException obj)
        {
            throw new InvalidFileException("Invalid pcked file format ");
        }

        catch(Exception e)
        {

        }
    }
}
*/