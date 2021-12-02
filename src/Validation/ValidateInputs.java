/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

/**
 * Validate name inputs based on length and characters
 * @author Jessica Moolenschot
 */
public class ValidateInputs
{
    private final static int FIRST_NAME_MAX = 20;//max length of firstName
    private final static int FIRST_NAME_MIN = 1;
    
    private final static int LAST_NAME_MAX = 30;
    private final static int LAST_NAME_MIN = 2;
    
    private final static int CHAR_NAME_MAX = 50;
    private final static int CHAR_NAME_MIN = 1;
    
    private final static int TEAM_NAME_MAX = 25;
    private final static int TEAM_NAME_MIN = 3;
    /**
     * Checks if firstname is valid
     * @param firstname firstname
     * @return Valid
     */
    public static boolean validateFirstName(String firstname)
    {
        String name = firstname.trim();
        return (name.length() >= FIRST_NAME_MIN && name.length() < FIRST_NAME_MAX);
    }
    /**
     * Checks if lastname is valid
     * @param lastname lastname
     * @return Valid
     */
    public static boolean validateLastName(String lastname)
    {
        String name = lastname.trim();
        return (name.length() >= LAST_NAME_MIN && name.length() < LAST_NAME_MAX);
    }
    /**
     * Checks if charactername is valid
     * @param charName charactername
     * @return Valid
     */
    public static boolean validateCharacterName(String charName)
    {
        String name = charName.trim();
        return (name.length() >= CHAR_NAME_MIN && name.length() < CHAR_NAME_MAX);
    }
    /**
     * checks if teamname is valid
     * @param teamName teamname
     * @return Valid
     */
    public static boolean validateTeamName(String teamName)
    {
        String name = teamName.trim();
        return (name.length() >= TEAM_NAME_MIN && name.length() < TEAM_NAME_MAX);
    }
}
