// Source File Name:   Crypto.java

package vCampus.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Crypto
{

    public Crypto()
    {
    }

    public static String hash(String type, String text)
    {
        String encodeStr = "";
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance(type);
            messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
            byte bytes[] = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for(int i = 0; i < bytes.length; i++)
            {
                String tmp = Integer.toHexString(bytes[i] & 0xff);
                if(tmp.length() == 1)
                    buffer.append("0");
                buffer.append(tmp);
            }

            encodeStr = buffer.toString();
        }
        catch(Exception err)
        {
            return "";
        }
        return encodeStr;
    }

    public static String passwordEncrypt(String password, String userName)
    {
        return "";
    }
}
