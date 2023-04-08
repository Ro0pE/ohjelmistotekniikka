import com.mycompany.blackjack.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    
    public PlayerTest() {
        
        
    }
    
    @Before
    public void setUp() {
       player = new Player("Boris");
    }
    
    @Test
    public void getNameReturnsCorrectName() {
        assertEquals("Boris", player.getName());
    }
    
    @Test
    public void getAccountBalanceReturnsCorrectBalance(){
        assertEquals(500.0, player.getAccountBalance(), 0.01);
    }
    
    @Test
    public void setBalanceChangesCurrentBalance() {
        player.setBalance(1250);
        assertEquals(1750.0, player.getAccountBalance(), 0.01);
        
    }
    @Test
    public void setWinnerWorks() {
        player.setWinner(); // jatka töstö
      
        
    }

}
