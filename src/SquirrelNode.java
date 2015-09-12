import java.util.ArrayList;

/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelNode {
  //узел
  ArrayList<SquirrelBranch> branchNames;
  public SquirrelNode(){
    branchNames=new ArrayList<SquirrelBranch>();
  }
  public void addBranch(SquirrelBranch branch){
    branchNames.add(branch);
  }
}
