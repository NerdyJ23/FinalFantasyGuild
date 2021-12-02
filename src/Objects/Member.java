/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;
import java.util.Date;

/**
 * An abstract class for Staff and Player to extend
 * @author Jessica Moolenschot
 */
abstract class Member implements Serializable
{
    private int memberID,guildID;
    private String firstName,lastName,region,datacenter,server;
    private Date joined;
    
    public Member(){}
    /**
     * Create Member from previous data
     * @param memberID
     * @param guildID
     * @param firstName
     * @param lastName
     * @param region
     * @param datacenter
     * @param server
     * @param joined 
     */
    public Member(int memberID, int guildID, String firstName, String lastName, String region, String datacenter, String server,Date joined)
    {
        this.memberID = memberID;
        this.guildID = guildID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.datacenter = datacenter;
        this.server = server;
        this.joined = joined;
    }
    
    //no ID + joined - for new Members
    /**
     * Create new Member (no ID or joined date)
     * @param firstName
     * @param lastName
     * @param region
     * @param datacenter
     * @param server 
     */
    public Member(String firstName, String lastName, String region, String datacenter, String server)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.datacenter = datacenter;
        this.server = server;
    }

    public int getMemberID()
    {
        return memberID;
    }

    public void setMemberID(int memberID)
    {
        this.memberID = memberID;
    }

    public int getGuildID()
    {
        return guildID;
    }

    public void setGuildID(int guildID)
    {
        this.guildID = guildID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
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

    public Date getJoined()
    {
        return joined;
    }

    public void setJoined(Date joined)
    {
        this.joined = joined;
    }
    
    @Override
    public String toString()
    {
        return String.format("[%s] %s %s in %s. %s %s",memberID,firstName,lastName,region,datacenter,server);
    }
    abstract String getRole();
}
