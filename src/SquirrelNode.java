import java.util.ArrayList;

/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelNode {
  //узел
  ArrayList<SquirrelBranch> branchs;

  public SquirrelNode(){
    branchs=new ArrayList<SquirrelBranch>();
  }
  public void addBranch(SquirrelBranch branch){
    branchs.add(branch);
  }
}
