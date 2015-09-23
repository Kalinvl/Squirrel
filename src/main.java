import java.util.ArrayList;

/**
 * Created by Vlad on 06.09.2015.
 */
public class main {
  public static void main (String[] args) throws Exception {
    //test1();
    //test2();
    //test3();
    //test4();
    //test5();
    //test6();
    //test7();
    //test8();
    //тестиорлвание склеивания узлов
    //test9();
    //тестиорлвание создания дерева и связей
    test10();
  }
  public static void test1() throws Exception {
    SquirrelMatrix matrix1=null;
    try {
      matrix1 = new SquirrelMatrix(3,4);
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (int i=0;i<3;i++){
      for (int j=0;j<4;j++){
        matrix1.setElem(i,j,new SquirrelCell(i,j));
      }
    }

    SquirrelMatrix matrix2=null;

    try {
      matrix2 = new SquirrelMatrix(3,4);
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (int i=0;i<3;i++){
      for (int j=0;j<4;j++){
        matrix2.setElem(i,j,new SquirrelCell(i*10,j*10));
      }
    }

    new SquirrelMatrixOperations().SquirrelMatrixDif(matrix2,matrix1).print();
    matrix1.print();
    matrix2.print();
  }
  public static void test2() throws Exception {
    SquirrelCell a = new SquirrelCell(-10,15);
    SquirrelCell b = new SquirrelCell(2,4);

    new SquirrelCell().inc(a,b).println();
    new SquirrelCell().dec(a, b).println();
    new SquirrelCell().mult(a, b).println();
    new SquirrelCell().divn(a, b).println();


    System.out.println(" ___________________________");
    new SquirrelCell(-10,15).inc(b).println();
    new SquirrelCell(-10,15).dec(b).println();
    new SquirrelCell(-10,15).mult(b).println();
    new SquirrelCell(-10,15).divn(b).println();
  }
  public static void test3() throws Exception {
    SquirrelDatabase database = new SquirrelReader().readDataBase("src/input.txt");
    ArrayList<String>list=new ArrayList<String>();
    list.add(-10 + "");
    list.add(0 + "");
    list.add(4 + "");
    list.add(5 + "");
    list.add(5 + "");
    list.add(7 + "");

    System.out.println(list.indexOf(77 + ""));  //  */


    /*HashMap<Integer,SquirrelBranch> branches = new HashMap<Integer, SquirrelBranch>();
    branches.put(1,new SquirrelBranch("R=10"));
    branches.put(2,new SquirrelBranch("R=20"));
    branches.put(1,new SquirrelBranch("R=30"));
    if(branches.get(3)==null)System.out.println("1");*/
  }
  public static void test4() throws Exception {
    SquirrelBranch br = new SquirrelBranch("R=10");
    //SquirrelBranch br2 = br.clone();

    /*HashMap<Integer,SquirrelBranch> branches = new HashMap<Integer, SquirrelBranch>();
    branches.put(1,new SquirrelBranch("R=10"));
    branches.put(2,new SquirrelBranch("R=20"));
    branches.put(1,new SquirrelBranch("R=30"));
    if(branches.get(3)==null)System.out.println("1");*/
  }
  public static void test5() throws Exception {
    SquirrelDatabase database = new SquirrelReader().readDataBase("src/input3.txt");

    ArrayList<String> tree= new SquirrelConverter().squirrelTreeDefiner(database.branchNames, database.branches, database.nodes);

    ArrayList<String> com= new SquirrelConverter().squirrelConnectionsDefiner(database.branchNames, tree);

    System.out.println("Тестирование данной функции");
    System.out.println("");
  }
  public static void test6() throws Exception {

    System.out.println("Тестирование матриц");
    /*
    SquirrelMatrix ferst = new SquirrelMatrix(2,3);

    ferst.setElem(0,0,new SquirrelCell(1,0));
    ferst.setElem(0,1,new SquirrelCell(3,0));
    ferst.setElem(0,2,new SquirrelCell(2,0));

    ferst.setElem(1,0,new SquirrelCell(0,0));
    ferst.setElem(1,1,new SquirrelCell(4,0));
    ferst.setElem(1,2,new SquirrelCell(-1,0));//*/

    //*
    SquirrelMatrix ferst = new SquirrelMatrix(1,5);
    ferst.setElem(0,0,new SquirrelCell(1,0));
    ferst.setElem(0,1,new SquirrelCell(3,0));
    ferst.setElem(0,2,new SquirrelCell(2,0));
    ferst.setElem(0,3,new SquirrelCell(5,0));
    ferst.setElem(0,4,new SquirrelCell(-5,0));
    //*/

    /*
    SquirrelMatrix second = new SquirrelMatrix(3,4);

    second.setElem(0,0,new SquirrelCell(2,0));
    second.setElem(0,1,new SquirrelCell(0,0));
    second.setElem(0,2,new SquirrelCell(-1,0));
    second.setElem(0,3,new SquirrelCell(1,0));

    second.setElem(1,0,new SquirrelCell(3,0));
    second.setElem(1,1,new SquirrelCell(-2,0));
    second.setElem(1,2,new SquirrelCell(1,0));
    second.setElem(1,3,new SquirrelCell(2,0));

    second.setElem(2,0,new SquirrelCell(0,0));
    second.setElem(2,1,new SquirrelCell(1,0));
    second.setElem(2,2,new SquirrelCell(2,0));
    second.setElem(2,3,new SquirrelCell(3,0));//*/

    SquirrelMatrix second = new SquirrelMatrix(5,1);

    second.setElem(0,0,new SquirrelCell(2,0));
    second.setElem(1,0,new SquirrelCell(0,0));
    second.setElem(2,0,new SquirrelCell(-1,0));
    second.setElem(3,0,new SquirrelCell(4,0));
    second.setElem(4,0,new SquirrelCell(-2,0));

    ferst.println();
    System.out.println();
    second.println();
    System.out.println();
    new SquirrelMatrixOperations().SquirrelMatrixMult(ferst,second).println();

    new SquirrelMatrixOperations().SquirrelMatrixMult(second,ferst).println();

    System.out.println("End");
  }
  public static void test7() throws Exception {

    System.out.println("Тестирование алгоритма поиска близжайшего контура");

    SquirrelDatabase database = new SquirrelReader().readDataBase("src/input2.txt");

    ArrayList<SquirrelBranch> loop= new SquirrelConverter().CreateLoop(database.branches.get(database.branchNames.get(14)),database.nodes,database.nodeNames);


    System.out.println("End");
  }
  public static void test8() throws Exception {

    System.out.println("Тестирование алгоритма поиска близжайшего контура");

    SquirrelDatabase database = new SquirrelReader().readDataBase("src/input5.txt");


    new SquirrelConverter().convertEBranches(database.branchNames, database.branches);

    //нужно дописать отдельную процедуру по удалению особых ветвей

    System.out.println("End");
  }
  public static void test9() throws Exception {

    System.out.println("Тестирование алгоритма объединения узлов");

    SquirrelDatabase database = new SquirrelReader().readDataBase("src/input5.txt");
/*
    SquirrelNode Node = new SquirrelNode();

    for (String branchNames : database.branchNames){
      database.branches.get(branchNames).println();
    }


    for (String nodeName : database.nodeNames){
      Node.mergeWith(database.nodes.get(nodeName));
    }
    System.out.println("End");
    for (SquirrelBranch nextBranch : Node.getBranches()){
      nextBranch.println();
    }

*/

        System.out.println();
    database.nodes.get(database.branchNames.get(0)).mergeWith(database.nodes.get(database.branchNames.get(1)));
    database.nodes.get(database.branchNames.get(2)).mergeWith(database.nodes.get(database.branchNames.get(3)));
    database.nodes.get(database.branchNames.get(0)).mergeWith(database.nodes.get(database.branchNames.get(2)));
    for (SquirrelBranch nextBranch : database.nodes.get(database.branchNames.get(2)).getBranches()){
      nextBranch.println();
    }
    System.out.println();
    for (SquirrelBranch nextBranch : database.nodes.get(database.branchNames.get(3)).getBranches()){
      nextBranch.println();
    }

    System.out.println("End");
  }

  public static void test10() throws Exception {

    System.out.println("Тестирование дерева и связей");

    SquirrelDatabase database = new SquirrelReader().readDataBase("src/input5.txt");

    SquirrelConverter converter = new SquirrelConverter();

    converter.convertEBranches(database.branchNames, database.branches);
    converter.convertJBranches(database.branchNames, database.branches, database.nodes, database.nodeNames);

    converter.compareNodes(database.branchNames, database.branches);

    ArrayList<String> tree = converter.squirrelTreeDefiner(database.branchNames, database.branches, database.nodes);
    //ArrayList<String> connections = converter.squirrelConnectionsDefiner(database.branchNames,tree);



    System.out.println("End");
  }
}
