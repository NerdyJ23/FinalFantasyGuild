/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.Date;

/**
 * Player class
 * @author Jessica Moolenschot
 */
public class Player extends Member
{
    private String characterName,characterRole,characterClass;

    public Player(){};
    /**
     * Create Player from previous data
     * @param characterName
     * @param characterRole
     * @param characterClass
     * @param memberID
     * @param guildID
     * @param firstName
     * @param lastName
     * @param region
     * @param datacenter
     * @param server
     * @param joined 
     */
    public Player(String characterName, String characterRole, String characterClass, int memberID, int guildID, String firstName, String lastName, String region, String datacenter, String server, Date joined)
    {
        super(memberID, guildID, firstName, lastName, region, datacenter, server, joined);
        this.characterName = characterName;
        this.characterRole = characterRole;
        this.characterClass = characterClass;
    }

    //new player
    /**
     * Create new Player (No playerID or join date)
     * @param characterName
     * @param characterRole
     * @param characterClass
     * @param firstName
     * @param lastName
     * @param region
     * @param datacenter
     * @param server 
     */
    public Player(String characterName, String characterRole, String characterClass, String firstName, String lastName, String region, String datacenter, String server)
    {
        super(firstName, lastName, region, datacenter, server);
        this.characterName = characterName;
        this.characterRole = characterRole;
        this.characterClass = characterClass;
    }

    public String getCharacterName()
    {
        return characterName;
    }

    public void setCharacterName(String characterName)
    {
        this.characterName = characterName;
    }

    public String getCharacterClass()
    {
        return characterClass;
    }

    public void setCharacterClass(String characterClass)
    {
        this.characterClass = characterClass;
    }

    public String getCharacterRole()
    {
        return characterRole;
    }

    public void setCharacterRole(String characterRole)
    {
        this.characterRole = characterRole;
    }
    
    /**
     * Always returns "Player"
     * @return 
     */
    @Override
    public String getRole()
    {
        return "Player";
    }
}
