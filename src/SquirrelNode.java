import java.util.ArrayList;

/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelNode {
  //узел
  SquirrelNode headNode = null;  //Ссылка на узел, содержащий ВСЮ информацию обо всех узлах (ссылки на узлы), связанных нулевым сопротивлением
  ArrayList <SquirrelNode> arrayOfNodes;  //Ссылки на узлы, содержащие информацию (ветви)

  private ArrayList<SquirrelBranch> branchs;

  public SquirrelNode(){
    branchs=new ArrayList<SquirrelBranch>();
  }
  public void addBranch(SquirrelBranch branch){
    branchs.add(branch);
  }

  public void addTailNode(SquirrelNode node){
    //Процедура добавление узлов - ссылок в головной узел
    if(node!=null) {
      if(node.headNode==null) {
        //У добавляемого узла нет ссылки
        if (arrayOfNodes == null) arrayOfNodes = new ArrayList<SquirrelNode>();
        arrayOfNodes.add(node);
        node.headNode = this;
      }else if(node.headNode==this) {
        //У добавляемого узла есть ссылка, но она на текущий узел
        if (arrayOfNodes == null) arrayOfNodes = new ArrayList<SquirrelNode>();
        //если добавляемый элемент не содержится уже в массиве
        if (!arrayOfNodes.contains(node))
          arrayOfNodes.add(node);
      } else {
        //У добавляемого узла есть ссылка и она на какой-то другой узел
        mergeTwoHead(node.headNode);
      }
    }
  }

  public ArrayList<SquirrelBranch> getBranches() {
    //если это не основной узел, то пусть основной разбирается
    if(headNode!=null)return headNode.getBranches();

    //если на этот узел никто не ссылается, то просто выдаём его параметы
    if(arrayOfNodes==null)return branchs;

    //если этот узел является основным - возвращаем склеенный массив
    ArrayList<SquirrelBranch> result = new ArrayList<SquirrelBranch>();

    for (SquirrelNode nextNode : arrayOfNodes){
      for (SquirrelBranch nextBranch : nextNode.branchs){
        if(!result.contains(nextBranch)) result.add(nextBranch);
      }
    }

    return result;
  }

  public void mergeWith(SquirrelNode second){
    //объединяет два узла
    if(headNode==null) headNode = new SquirrelNode();

    headNode.addTailNode(this);
    headNode.addTailNode(second);
  }

  private void mergeTwoHead(SquirrelNode second){
    //объединяет два головных узла

    if(second!=null) {
      arrayOfNodes.addAll(second.arrayOfNodes);
      for (SquirrelNode nextNode : second.arrayOfNodes) {
        nextNode.headNode = this;
      }

      second.arrayOfNodes.clear();
    }
  }
}
