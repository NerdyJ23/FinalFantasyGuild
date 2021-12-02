/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;
import java.util.Date;

/**
 *  Guild class
 * @author Jessica Moolenschot
 */
public class Guild implements Serializable
{
    private int guildID;
    private String name,region,datacenter,server;
    private boolean pvp;
    private Date founded;
    
    public Guild(){}

    /**
     * Creates guild from previous data
     * @param guildID
     * @param name
     * @param region
     * @param datacenter
     * @param server
     * @param pvp
     * @param founded 
     */
    public Guild(int guildID, String name, String region, String datacenter, String server, boolean pvp, Date founded)
    {
        this.guildID = guildID;
        this.name = name;
        this.region = region;
        this.datacenter = datacenter;
        this.server = server;
        this.pvp = pvp;
        this.founded = founded;
    }

    /**
     * Creates new guild (No ID)
     * @param name
     * @param region
     * @param datacenter
     * @param server
     * @param pvp
     * @param founded 
     */
    public Guild(String name, String region, String datacenter, String server, boolean pvp, Date founded)
    {
        this.name = name;
        this.region = region;
        this.datacenter = datacenter;
        this.server = server;
        this.pvp = pvp;
        this.founded = founded;
    }

    public int getGuildID()
    {
        return guildID;
    }

    public void setGuildID(int guildID)
    {
        this.guildID = guildID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getDatacenter()
    {
        return datacenter;
    }

    public void setDatacenter(String datacenter)
    {
        this.datacenter = datacenter;
    }

    public String getServer()
    {
        return server;
    }

    public void setServer(String server)
    {
        this.server = server;
    }

    public boolean isPvp()
    {
        return pvp;
    }

    public void setPvp(boolean pvp)
    {
        this.pvp = pvp;
    }

    public Date getFounded()
    {
        return founded;
    }

    public void setFounded(Date founded)
    {
        this.founded = founded;
    }
    
    @Override
    public String toString()
    {
        return name;
        //return String.format("Guild %s",name);
        //return String.format("[%s] Guild %s\nRegion: %s, Located at %s in server %s. \nFounded %s",guildID,name,region,datacenter,server,founded);
    }
}
