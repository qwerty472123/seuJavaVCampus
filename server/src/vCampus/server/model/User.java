package vCampus.server.model;

import vCampus.utility.Crypto;

public class User
{

    public User()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public boolean equalsPassword(String password)
    {
        return Crypto.passwordEncrypt(password, getUserName()) == this.password;
    }

    public void newPassword(String password)
    {
        this.password = Crypto.passwordEncrypt(password, getUserName());
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    private int id;
    private String userName;
    private String password;
    private String role;
    private String realName;
}
