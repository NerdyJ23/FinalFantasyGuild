/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *  Data class that contains int-indexed enum for all FF14 classes
 * @author jessi
 */
public enum Classes
{
    //basic classes first
    GLADIATOR(0),
    MARAUDER(1),
    PUGILIST(2),
    LANCER(3),
    ROGUE(4),
    ARCHER(5),
    THAUMATURGE(6),
    ARCANIST(7),
    CONJURER(8),
    
    //jobs
    PALADIN(9),
    WARRIOR(10),
    MONK(11),
    DRAGOON(12),
    NINJA(13),
    BARD(14),
    BLACK_MAGE(15),
    SUMMONER(16),
    SCHOLAR(17),
    WHITE_MAGE(18),
    BLUE_MAGE(19),
    DARK_KNIGHT(20),
    ASTROLOGIAN(21),
    MACHINIST(22),
    SAMURAI(23),
    RED_MAGE(24),
    GUNBREAKER(25),
    DANCER(26);

    private final int pos;
    /**
     * Sets the internal value position
     * @param pos 
     */
    private Classes(int pos)
    { 
        this.pos = pos;
    }
    /**
     * Returns the position
     * @return 
     */
    public int getValue()
    {
        return pos;
    }
}
