import java.util.*;

/**
 * Created by Vlad on 07.09.2015.
 */
public class SquirrelConverter {
  //конвертирует (как бы странно это ни звучало)

  //Алгоритм можно оптимизировать.
  //Теперь в узлах есть ссылки на ветви.
  //Алгоритм неверен
  public ArrayList<String> squirrelTreeDefiner2(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches, HashMap<String,SquirrelNode> nodes){
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

  public ArrayList<String> squirrelTreeDefiner(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches, HashMap<String,SquirrelNode> nodes){


    return null;
  }


  public ArrayList<String> squirrelConnectionsDefiner(ArrayList<String> branchNames, ArrayList<String> tree){
    //из исходных данных и ветвей формирует связи (по весу не упорядочено!)

    //контейнеры, в которые будут складываться ветви дерева
    ArrayList<String> connections = new ArrayList<String>();

    for (String aNodeBasket : branchNames) {
      if (!tree.contains(aNodeBasket)) connections.add(aNodeBasket);
    }

    return connections;
  }

  public SquirrelMatrix createMatrixA(ArrayList<String> tree, ArrayList<String> connections){
    //формирует матрицу A из ветвей дерева и связей

    //SquirrelMatrix newMatrix = new SquirrelMatrix();
    return null;
  }

  public ArrayList<SquirrelBranch> CreateLoop(SquirrelBranch branch,HashMap<String,SquirrelNode> nodes, ArrayList<String> nodeNames){
    class Segment{
      // данный клас является аналогом узлов, но ещё и с ветвью, по которой до него дошли
      SquirrelNode node;
      SquirrelBranch branch;

      public Segment(SquirrelNode node){
        this.node=node;
      }
    }
    HashMap<String,Segment> segments = new HashMap<String, Segment>();
    //Создаём массив сегментов, запихивая в HashMap под тем-же именем, что и его узел
    for (String nodeName : nodeNames){
      segments.put(nodeName,new Segment(nodes.get(nodeName)));
    }

    SquirrelNode startNode = branch.startNode;
    SquirrelNode finishNode = branch.finishNode;

    ArrayList<SquirrelBranch> queue = new ArrayList<SquirrelBranch>();

    //добавляем соседние ветви в очередь
    for (SquirrelBranch nextBranch : startNode.getBranches()){
      if(nextBranch!=branch)queue.add(queue.size(),nextBranch);
    }

    //называем исходный узел учтённым
    segments.get(branch.startNodeNum).branch=branch;

    //цикл упорядоченного поиска конечного узла
    while (!queue.isEmpty()){
      SquirrelBranch nextBranch = queue.remove(0);

      //Если данная ветвь подходит к искомому узлу, то завершаем цикл и добавляем все найденный ветви в исходный список
      if((nextBranch.startNode==finishNode)|(nextBranch.finishNode==finishNode)){
        ArrayList<SquirrelBranch> result = new ArrayList<SquirrelBranch>();

        //result.add(nextBranch);

        //берём последний сегмент чтоб с его помощью найти предыдущую ветвь
        Segment lastSegment = null;
        if(nextBranch.startNode==finishNode){
          lastSegment=segments.get(nextBranch.startNodeNum);
        }else{
          lastSegment=segments.get(nextBranch.finishNodeNum);
        }
        lastSegment.branch=nextBranch;

        while (true){
          //добавляюм предшествующую ветвь в массив
          result.add(result.size(),lastSegment.branch);

          SquirrelNode lastNode = null;
          String lastNodeName=null;

          if(lastSegment.branch.startNode!=lastSegment.node){
            lastNode=lastSegment.branch.startNode;
            lastNodeName=lastSegment.branch.startNodeNum;
          }else{
            lastNode=lastSegment.branch.finishNode;
            lastNodeName=lastSegment.branch.finishNodeNum;
          }

          // Проверка - дошли ли мы до конца
          if(lastNode==startNode){
            segments.clear();
            return result;
          }


          // находим предыдущий узел и заменяем текущий сегмент предыдущим
          lastSegment=segments.get(lastNodeName);
        }


      }

      //если у данной ветви есть узел, который мы ещё не проходили, то добавляем ветвь в список, а узел отмечаем
      if(segments.get(nextBranch.startNodeNum).branch==null){
        //Добавляем ветвь, по которой нашли сегмент
        segments.get(nextBranch.startNodeNum).branch=nextBranch;
        //Нашел недобавленный сегмент. Теперь должен добавить все его непросмотренные ветви в список (если какая-то из этих ветвей повторится - то это не страшно)
        for (SquirrelBranch nextSegmentBranch : nextBranch.startNode.getBranches()){
          if(nextSegmentBranch!=nextBranch)queue.add(queue.size(),nextSegmentBranch);
        }
      }else if(segments.get(nextBranch.finishNodeNum).branch==null){
        //Добавляем ветвь, по которой нашли сегмент
        segments.get(nextBranch.finishNodeNum).branch=nextBranch;
        //Нашел недобавленный сегмент. Теперь должен добавить все его непросмотренные ветви в список (если какая-то из этих ветвей повторится - то это не страшно)
        for (SquirrelBranch nextSegmentBranch : nextBranch.finishNode.getBranches()){
          if(nextSegmentBranch!=nextBranch)queue.add(queue.size(),nextSegmentBranch);
        }
      }
    }

    return null;
  }

  public void convertJBranches(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches, HashMap<String,SquirrelNode> nodes, ArrayList<String> nodeNames){

    //HashMap<String,SquirrelBranchExtended> result = new HashMap<String, SquirrelBranchExtended>();

    for(String nextBranch : branchNames){
      //проверка на особую ветвь - только источник тока
      /*if(!deleteJBranchIfNeed(branches.get(nextBranch),nodes,nodeNames))
        result.put(nextBranch,new SquirrelBranchExtended(branches.get(nextBranch),W));*/
      pickJBranchIfNeed(branches.get(nextBranch), nodes, nodeNames);
    }
  }

  private boolean pickJBranchIfNeed(SquirrelBranch branch, HashMap<String,SquirrelNode> nodes, ArrayList<String> nodeNames){

    if(branch.JBranch){
      //Если в ветви только один элемент - источник тока
      //# Тут нужно добавить обработку ошибки
      ArrayList<SquirrelBranch> loop = CreateLoop(branch,nodes,nodeNames);

      //if(loop!=null)

      // для определения направления нужен дополнительный узел
      SquirrelNode previousNode = branch.finishNode;
      for(SquirrelBranch nextBranch : loop){
        if (nextBranch.finishNode==previousNode){
          //Следующая ветвь в направлении обхода
          nextBranch.incJ(branch.J);
          //nextBranch.incJ(branch.addJ);
          previousNode=nextBranch.startNode;
        }else if (nextBranch.startNode==previousNode){
          //Следующая ветвь против направления обхода
          nextBranch.decJ(branch.J);
          //nextBranch.decJ(branch.addJ);
          previousNode=nextBranch.finishNode;
        } else {
          System.out.println("Такого контура не существует");
        }
      }

      return true;
    }

    return false;
  }

  public void convertEBranches(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches){

    //HashMap<String,SquirrelBranchExtended> result = new HashMap<String, SquirrelBranchExtended>();

    for(String nextBranch : branchNames){
      //проверка на особую ветвь - только источник напряжения
      pickEBranchIfNeed(branches.get(nextBranch));
    }

    //HashSet<SquirrelBranch> EBranch = new HashSet<SquirrelBranch>();
  }

  private boolean pickEBranchIfNeed(SquirrelBranch branch){

    if(branch.EBranch){
      //Если в ветви только один элемент - источник тока
      //# Тут нужно добавить обработку ошибки
      SquirrelNode Node = branch.finishNode;

      ArrayList<SquirrelBranch> list = Node.getBranches();

      //if(loop!=null)

      // для определения направления нужен дополнительный узел

      for(SquirrelBranch nextBranch : list){
        if(nextBranch!=branch)
        if (nextBranch.startNode==Node){
          //Следующая ветвь выходит из узла
          nextBranch.incE(branch.E);
          //nextBranch.incE(branch.addE);
        }else if (nextBranch.finishNode==Node){
          //Следующая ветвь входит в узел
          nextBranch.decE(branch.E);
          //nextBranch.decE(branch.addE);
        } else {
          System.out.println("Такого контура не существует");
        }
      }

      return true;
    }

    return false;
  }

  //Убирает особые ветви, если надо
  public HashMap<String,SquirrelBranch> newBranches(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches, boolean removeJ){
    HashMap<String,SquirrelBranch> result = new HashMap<String, SquirrelBranch>();
    for (String nextBranchName : branchNames){
      if(removeJ) {
        if (!branches.get(nextBranchName).JBranch) result.put(nextBranchName, branches.get(nextBranchName));
      } else {
        result.put(nextBranchName, branches.get(nextBranchName));
      }
    }
    return result;
  }

  public HashMap<String,SquirrelNode> newNodes(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches, boolean removeE){
    HashMap<String,SquirrelNode> result = new HashMap<String, SquirrelNode>();
    for (String nextBranchName : branchNames) {
      if (removeE) {
        if (!branches.get(nextBranchName).EBranch) {
          //если ветвь не имеет сопротивления - объединяем её с соседней
          branches.get(nextBranchName).startNode.mergeWith(branches.get(nextBranchName).finishNode);
        }
      }
      if (!result.containsKey(branches.get(nextBranchName).startNodeNum))
        result.put(branches.get(nextBranchName).startNodeNum, branches.get(nextBranchName).startNode);
      if (!result.containsKey(branches.get(nextBranchName).finishNodeNum))
        result.put(branches.get(nextBranchName).finishNodeNum, branches.get(nextBranchName).finishNode);
    }
    return result;
  }

  public void compareNodes(ArrayList<String> branchNames, HashMap<String,SquirrelBranch> branches){
    /**
     * Если узлы связаны ветвью без сопротивления - они объединяются
     */
    for (String nextBranchName : branchNames) {
      if (!branches.get(nextBranchName).EBranch) {
        //если ветвь не имеет сопротивления - объединяем её с соседней
        branches.get(nextBranchName).startNode.mergeWith(branches.get(nextBranchName).finishNode);
      }
    }
  }

}
