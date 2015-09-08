import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Vlad on 07.09.2015.
 */
public class SquirrelConverter {
  //конвертирует (как бы странно это ни звучало)

  public ArrayList<String> SquirrelTreeDefiner(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches, HashMap<String,SquirrelNode> nodes){
    //из исходных данных собирает дерево
    //алгоритм Дейкстры-Прима (уже, видимо, иной алгоритм - ветви в приоритете)

    //Создание нового списка для возможности вычёркивания ветвей при работе алгоритма
    TreeMap<Integer,String> branchBasket = new TreeMap<Integer, String>();

    //заполнение в порядке сортировки по весам
    for (int i=0;i<branchNames.size();i++){
      branchBasket.put(branches.get(branchNames.get(i)).weight, branchNames.get(i));
    }


    //Создание нового списка для возможности вычёркивания узлов при работе алгоритма
    ArrayList<String> nodeBasket = new ArrayList<String>();

    //конструкция пуста изначально
    /*for (int i=0;i<nodeNames.size();i++){
      nodeBasket.add(nodeNames.get(i));
    } */

    //контейнеры, в которые будут складываться ветви дерева
    ArrayList<String> tree = new ArrayList<String>();


    //пока все ветки не обойдём:
    while (!branchBasket.isEmpty()) {
      //берём ветку, заодно удаляя её из корзины
      String branchName = branchBasket.get(branchBasket.firstKey());
      SquirrelBranch branch = branches.get(branchName);
      branchBasket.remove(branchBasket.firstKey());

      //Смотрим - добавляет ли данная ветвь новые узлы в дерево
      boolean indStart=false;
      boolean indFinish=false;

      //конструкция заменилась автоматически, в оригинале это был обычный цикл for
      for (String aNodeBasket : nodeBasket) {
        if (branch.startNode == nodes.get(aNodeBasket)) indStart = true;
        if (branch.finishNode == nodes.get(aNodeBasket)) indFinish = true;
      }

      if((!indStart)|(!indFinish)){
        //если данная ветвь добавляет новые узлы в дерево графа
        //данный метод мне не нравится - не хотел обращаться к названию узла через ветвь, но иначе, быстро- никак
        if(!indStart) nodeBasket.add(branch.startNodeNum);
        if(!indFinish) nodeBasket.add(branch.finishNodeNum);

        tree.add(branchName);
      }
    }


    return tree;
  }

  public ArrayList<String> SquirrelConnectionsDefiner(ArrayList<String> branchNames, ArrayList<String> tree){
    //из исходных данных и ветвей формирует связи (по весу не упорядочено!)

    //контейнеры, в которые будут складываться ветви дерева
    ArrayList<String> connections = new ArrayList<String>();

    for (String aNodeBasket : branchNames) {
      if (!tree.contains(aNodeBasket)) connections.add(aNodeBasket);
    }

    return connections;
  }

  public SquirrelMatrix CreateMatrixA(ArrayList<String> tree, ArrayList<String> connections){
    //формирует матрицу A из ветвей дерева и связей

    return null;
  }

}
