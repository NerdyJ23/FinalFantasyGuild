/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.Date;

/**
 * Staff class
 * @author Jessica Moolenschot
 */
public class Staff extends Member
{
    private Role role;
    public static enum Role
    {
        ENFORCER,
        ADMINISTRATOR
    }
    
    public Staff(){}
    /**
     * Create Staff from previous data
     * @param role
     * @param memberID
     * @param guildID
     * @param firstName
     * @param lastName
     * @param region
     * @param datacenter
     * @param server
     * @param joined 
     */
    public Staff(Role role, int memberID, int guildID, String firstName, String lastName, String region, String datacenter, String server, Date joined)
    {
        super(memberID, guildID, firstName, lastName, region, datacenter, server, joined);
        this.role = role;
    }
    /**
     * Create new Staff (no memberID or joined)
     * @param firstName
     * @param lastName
     * @param region
     * @param datacenter
     * @param server 
     */
    public Staff(String firstName, String lastName, String region, String datacenter, String server)
    {
        super(firstName, lastName, region, datacenter, server);
    }
    
    @Override
    /**
     * Returns Role.name of Staff members role
     */
    public String getRole()
    {
        return role.name();
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " ROLE: " + role;
    }
    
    public void setRole(Role role)
    {
        this.role = role;
    }
}
