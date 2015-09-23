import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelDatabase {
  private double frequency;
  private double W;
  private double angle;

  HashMap<String,SquirrelBranch> branches; //файлы с параметрами ветвей
  ArrayList<String> branchNames; // названия ветвей
  HashMap<String,SquirrelNode> nodes; //файлы с параметрами узлов
  ArrayList<String> nodeNames; //названия узлов

  public SquirrelDatabase() {
    branches=new HashMap<String, SquirrelBranch>();
    branchNames=new ArrayList<String>();

    nodes=new HashMap<String, SquirrelNode>();
    nodeNames=new ArrayList<String>();
  }

  public void analyze(){
    //метод предназначен для анализа сотавляющих после считывания из файла

    //часть - 1 - определение узлов
    SquirrelBranch branch=null;

    for (int i=0;i<branchNames.size();i++){
      //вытаскиваем очередную ветку
      branch=branches.get(branchNames.get(i));
      //тут ошибки быть не должно, но можно дописать кусок кода
      //вытаскиваем из ветки её узлы
      String node1=branch.startNodeNum;
      String node2=branch.finishNodeNum;

      //добавляем начальный узел, если такого ещё нет
      if(nodeNames.indexOf(node1)==-1){
        SquirrelNode startNode = new SquirrelNode();
        //записываем ссылку на узел в ветвь
        branch.startNode=startNode;
        nodes.put(node1,startNode);
        //добавляем узел в список имён
        nodeNames.add(node1);
        //добавляем ветвь в параметры узла
        startNode.addBranch(branch);
      }else {
        //если есть - добываем его и записываем в ветвь
        SquirrelNode node = nodes.get(nodeNames.get(nodeNames.indexOf(node1)));
        branch.startNode=node;
        //добавляем ветвь в параметры узла
        node.addBranch(branch);
      }

      //добавляем конечный узел, если такого ещё нет
      if(nodeNames.indexOf(node2)==-1){
        SquirrelNode finishNode = new SquirrelNode();
        //записываем ссылку на узел в ветвь
        branch.finishNode=finishNode;
        nodes.put(node2,finishNode);
        //добавляем узел в список имён
        nodeNames.add(node2);
        //добавляем ветвь в параметры узла
        finishNode.addBranch(branch);
      }else {
        //если есть - добываем его и записываем в ветвь
        SquirrelNode node = nodes.get(nodeNames.get(nodeNames.indexOf(node2)));
        branch.finishNode=node;
        //добавляем ветвь в параметры узла
        node.addBranch(branch);
      }
    }

    //часть - 2 - пересчёт параметров ветвей в соответствии с установленной частотой
    //необходимо добавить учёт угла и спецефичного метода задания источников

    W=2*Math.PI*frequency;

    for (int i=0;i<branchNames.size();i++){
      //вытаскиваем очередную ветку
      branch=branches.get(branchNames.get(i));
      branch.analyze(W);
    }

    //часть - 3 - задание весов лининй

    //не должно быть равных весов линий ! (иначе алгоритм построения дерева загнётся)

    //пока упрощённо - просто по номерам в порядке добавления
    for (int i=0;i<branchNames.size();i++){
      branches.get(branchNames.get(i)).weight=i;
    }

    //часть - 4 - задание начальных условий


  }


  public void addBranch(int num, SquirrelBranch branch){
    //добавление ветки в Map и её идентификатора в ArrayList
    branches.put(num+"",branch);
    branchNames.add(num+"");
  }
  public double getFrequency() {
    return frequency;
  }
  public void setFrequency(double frequency) {
    this.frequency = frequency;
  }
  public double getAngle() {
    return angle;
  }
  public void setAngle(double angle) {
    this.angle = angle;
  }
  public double getW() {
    return W;
  }
}
