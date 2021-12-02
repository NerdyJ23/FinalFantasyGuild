/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Console.Trace;
import Database.DB;

import Objects.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *  Data Access Layer class, all the important stuff happens here
 * @author Jessica Moolenschot, 2021
 */
public class DAL
{
    
    private static final String USERNAME = "moolenj";
    private static final String PASSWORD = "admin";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    
    private static final String DB = "finalfantasy";
    
    private DB database = null;
    public static enum Table{
    GUILD,
    MEMBER
    }//ENUM for populating table
    /**
     * Default Constructor
     */
    public DAL()
    {
        database = new DB(URL,USERNAME,PASSWORD,DB);
    }
    /**
     * Connect to database
     */
    public void connect()
    {
        database = new DB(URL,USERNAME,PASSWORD,DB);
    }
    /**
     * Alias for database.close()
     */
    public void disconnect()
    {
        database.close();
    }
    /**
     * Closes database connection
     */
    public void close()
    {
        database.close();
    }
    /**
     * Executes a query that does not return a ResultSet
     * @param sql
     * @return Success
     */
    public boolean executeNonQuery(String sql)
    {
        try
        {
            database.connect();
            database.executeNonQuery(sql);
        }
        catch(Exception e)
        {
            Trace.error("Could not execute query: " + sql);
            Trace.error(e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * Executes multiple queries that are ; delimited, that do not return a ResultSet
     * @param sql Success
     * @throws SQLException 
     */
    public void executeNonQueryBatch(String sql) throws SQLException
    {
        database.connect();
        String[] batch = sql.split(";");
        for(String s : batch)
            database.executeNonQuery(s);
    }
    public ResultSet executeQuery(String sql)
    {
        ResultSet r = null;
        try
        {
            database.connect();
            r = database.executeQuery(sql);
        }
        catch(Exception e)
        {
            Trace.error("Could not execute query");
            Trace.error((e.getMessage()));
        }
        return r;
    }
    /**
     * Adds a Member (Staff or Player) to the database
     * @param a Member
     * @return Success
     */
    public boolean addMember(Object a)
    {
        if(a.getClass() == Player.class) //WTF this worked first try??
        {
            Trace.log("Object was a player");
            Player p = (Player)a;//cast object back into a player to use methods
            
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String sql = String.format("INSERT INTO members (memberID,guildID,firstName,lastName,characterName,characterRole,class,region,datacenter,server,joined)  "
                    +       "VALUES (%s,%s,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"
                    ,p.getMemberID(), p.getGuildID(),p.getFirstName(),p.getLastName() //player info
                    ,p.getCharacterName(),p.getCharacterRole(),p.getCharacterClass(),//character info
                    p.getRegion(),p.getDatacenter(),p.getServer(),f.format(p.getJoined()) //generic info
            );
            Trace.log(sql);
            //add player to db
            return executeNonQuery(sql);
        }
        else if(a.getClass() == Staff.class)
        {
            Trace.log("Object was a staff member");
            Staff s = (Staff)a;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String sql = String.format("INSERT INTO members (memberID, guildID,firstName,lastName,region,datacenter,server,joined,playerRole) "
                    + "VALUES (%s, %s,'%s','%s','%s','%s','%s','%s','%s')", 
                    s.getMemberID(), s.getGuildID(),s.getFirstName(),s.getLastName()
                    ,s.getRegion(),s.getDatacenter(),s.getServer(),f.format(s.getJoined()),
                    s.getRole()
                    );
            //add staff member to db
            Trace.log(sql);
            return executeNonQuery(sql);
        }
        return false;
    }
    /**
     * Get ArrayList of all Guilds in database
     * @return GuildList
     */
    public ArrayList<Guild> getGuilds()
    {
        ArrayList<Guild> li = new ArrayList<>();
        String sql = "SELECT * FROM guild";
        ResultSet r = executeQuery(sql);
        try
        {
            while(r.next())
            {
                Guild g = new Guild();
                g.setGuildID(r.getInt("guildID"));
                g.setName(r.getString("name"));
                g.setPvp(r.getBoolean("pvp"));
                g.setRegion(r.getString("region"));
                g.setDatacenter(r.getString("datacenter"));
                g.setServer(r.getString("server"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(r.getString("founded"));
                g.setFounded(date);
                
                li.add(g);
            }
            
        }
        catch(SQLException e)
        {
            Trace.error("Sql Exception");
        }
        catch(ParseException e)
        {
            Trace.error("Date parse exception");
            Trace.error(e.getMessage());
        }
        return li;
    }
    /**
     * Updates a member (deletes then inserts)
     * @param o Member
     * @return Success
     */
    public boolean updateMember(Object o)
    {
        if(o.getClass() == Player.class)
            deleteMember(((Player)o).getMemberID());
        else if(o.getClass() == Staff.class)
            deleteMember(((Staff)o).getMemberID());

        return addMember(o);
    }
    /**
     * Delete a member from the database by ID
     * @param memberID 
     */
    public void deleteMember(int memberID)
    {
        String sql = "DELETE FROM `members` WHERE memberID = '" + memberID + "'";
        
        executeNonQuery(sql);
    }
    /**
     * Get a filtered list of Members from the database
     * @param sql
     * @return FilteredList
     */
    public ArrayList<Object> getMembers(String sql)
    {
        ArrayList<Object> li = new ArrayList<>();
        //String sql = "SELECT * FROM members";
        ResultSet r = executeQuery(sql);
        try
        {
            while(r.next())
            {
                String dummy = r.getString("playerRole");
                if(r.wasNull())//if no role, item is player
                {
                    Player s = new Player();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setCharacterName(r.getString("characterName"));
                    s.setCharacterRole(r.getString("characterRole"));
                    s.setCharacterClass(r.getString("class"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    li.add(s);
                }
                else
                {
                    Staff s = new Staff();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                        s.setRole(Staff.Role.valueOf(r.getString("playerRole")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    catch(NullPointerException e)
                    {
                        Trace.error("null role?");
                    }
                    li.add(s);
                }
            }
        }
        catch(SQLException e)
        {
            Trace.error(e.getMessage());
        }
        return li;
    }
    /**
     * Get ArrayList of Members from the database (contains mixed classes of Staff and Player)
     * @return MemberList
     */
    public ArrayList<Object> getMembers()
    {
        ArrayList<Object> li = new ArrayList<>();
        String sql = "SELECT * FROM members";
        ResultSet r = executeQuery(sql);
        try
        {
            while(r.next())
            {
                String dummy = r.getString("playerRole");
                if(r.wasNull())//if no role, item is player
                {
                    Player s = new Player();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setCharacterName(r.getString("characterName"));
                    s.setCharacterRole(r.getString("characterRole"));
                    s.setCharacterClass(r.getString("class"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    li.add(s);
                }
                else
                {
                    Staff s = new Staff();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                        s.setRole(Staff.Role.valueOf(r.getString("playerRole")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    catch(NullPointerException e)
                    {
                        Trace.error("null role?");
                    }
                    li.add(s);
                }
            }
        }
        catch(SQLException e)
        {
            Trace.error(e.getMessage());
        }
        return li;
    }
    /**
     * Get all members on a team (contains mixed classes of Staff and Player)
     * @param guildID
     * @return TeamMemberList
     */
    public ArrayList<Object> getMembersByTeam(int guildID)
    {
        ArrayList<Object> li = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE guildID = " + guildID;
        ResultSet r = executeQuery(sql);
        try
        {
            while(r.next())
            {
                String dummy = r.getString("playerRole");
                if(r.wasNull())//if no role, item is player
                {
                    Player s = new Player();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setCharacterName(r.getString("characterName"));
                    s.setCharacterRole(r.getString("characterRole"));
                    s.setCharacterClass(r.getString("class"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    li.add(s);
                }
                else
                {
                    Staff s = new Staff();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                        s.setRole(Staff.Role.valueOf(r.getString("playerRole")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    catch(NullPointerException e)
                    {
                        Trace.error("null role?");
                    }
                    li.add(s);
                }
            }
        }
        catch(SQLException e)
        {
            Trace.error(e.getMessage());
        }
        return li;
    }
    /**
     * Get member from database by ID (returns either Staff or Player)
     * @param memberID
     * @return Member
     */
    public Object getMemberByID(int memberID)
    {
        //ArrayList<Object> li = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE memberID = " + memberID;
        ResultSet r = executeQuery(sql);
        try
        {
            while(r.next())
            {
                String dummy = r.getString("playerRole");
                if(r.wasNull())//if no role, item is player
                {
                    Player s = new Player();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setCharacterName(r.getString("characterName"));
                    s.setCharacterRole(r.getString("characterRole"));
                    s.setCharacterClass(r.getString("class"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    return (Object) s;
                }
                else
                {
                    Staff s = new Staff();
                    
                    s.setMemberID(r.getInt("memberID"));
                    s.setGuildID(r.getInt("guildID"));
                    
                    s.setFirstName(r.getString("firstName"));
                    s.setLastName(r.getString("lastName"));
                    
                    s.setRegion(r.getString("region"));
                    s.setDatacenter(r.getString("datacenter"));
                    s.setServer(r.getString("server"));
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try
                    {
                        s.setJoined(format.parse(r.getString("joined")));
                        s.setRole(Staff.Role.valueOf(r.getString("playerRole")));
                    }
                    catch(ParseException e)
                    {
                        Trace.error("Could not parse date!");
                    }
                    catch(NullPointerException e)
                    {
                        Trace.error("null role?");
                    }
                    return (Object) s;
                }
            }
        }
        catch(SQLException e)
        {
            Trace.error(e.getMessage());
        }
        return null;
    }
    /**
     * Add guild to database
     * @param g
     * @return Success
     */
    public boolean addGuild(Guild g)
    {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");//should really make this a global variable at this point

        String sql = String.format("INSERT INTO guild VALUES (%s,'%s',%s,'%s','%s','%s','%s')",
                g.getGuildID(),g.getName(),g.isPvp(),g.getRegion(),g.getDatacenter(),g.getServer(),f.format(g.getFounded()));
        
        if(executeNonQuery(sql))
        {
            Trace.log("Restored guild " + g);
            return true;
        }
        else
        {
            Trace.error("Could not restore guild " + g);
        }
        return false;
    }
    /**
     * Returns maxID +1
     * @param dbTable Either find new ID for Guild or Member
     * @return ID
     */
    public int getNewID(Table dbTable)
    {
        String sql = "";
        ResultSet r = null;
        switch(dbTable)
        {
            case GUILD:
                sql = "SELECT guildID FROM guild ORDER BY guildID DESC LIMIT 1";
                r = executeQuery(sql);
                try
                {
                    while(r.next())
                    {
                        return r.getInt("guildID")+1;
                    }
                }
                catch(SQLException e)
                {
                    Trace.error("No members, or another error");
                    Trace.error(e.getMessage());
                }
                break;
            case MEMBER:
                sql = "SELECT memberID FROM members ORDER BY memberID DESC LIMIT 1";
                r = executeQuery(sql);
                try
                {
                    while(r.next())
                    {
                        return r.getInt("memberID")+1;
                    }
                }
                catch(SQLException e)
                {
                    Trace.error("No members, or another error");
                    Trace.error(e.getMessage());
                }
                break;
        }
        return 1;
    }
    /**
     * Thanos snaps the database to a blank slate
     */
    public void wipeDatabase()
    {
        String createGuildTable = "DROP TABLE IF EXISTS `guild`;CREATE TABLE `guild` (" +
"  `guildID` int(11) NOT NULL," +
"  `name` varchar(25) NOT NULL," +
"  `pvp` tinyint(1) NOT NULL," +
"  `region` varchar(15) NOT NULL," +
"  `datacenter` varchar(10) NOT NULL," +
"  `server` varchar(15) NOT NULL," +
"  `founded` date NOT NULL," +
"  PRIMARY KEY (`guildID`)" +
") ENGINE=MyISAM  DEFAULT CHARSET=utf8;";
        
        String createMemberTable = "DROP TABLE IF EXISTS `members`;CREATE TABLE `members` (" +
            "`memberID` int(11) NOT NULL," +
            "  `guildID` int(11) NOT NULL," +
            "  `firstName` varchar(20) NOT NULL," +
            "  `lastName` varchar(30) NOT NULL," +
            "  `characterName` varchar(50) DEFAULT NULL," +
            "  `characterRole` varchar(6) DEFAULT NULL," +
            "  `class` varchar(27) DEFAULT NULL COMMENT 'amount of jobs and roles available'," +
            "  `region` varchar(15) NOT NULL," +
            "  `datacenter` varchar(10) NOT NULL," +
            "  `server` varchar(15) NOT NULL," +
            "  `joined` date NOT NULL," +
            "  `playerRole` varchar(10) DEFAULT NULL," +
            "  PRIMARY KEY (`memberID`)," +
            "  KEY `guildID` (`guildID`)" +
            ") ENGINE=MyISAM  DEFAULT CHARSET=utf8;";
                
        try
        {
            executeNonQueryBatch(createGuildTable + createMemberTable);
        }
        catch(SQLException e)
        {
            Trace.error("Could not refresh db");
            Trace.error(e.getMessage());
            System.err.println(e);
        }
        
        //Trace.log(createDatabase);

        
    }
}
