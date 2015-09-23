import java.util.StringTokenizer;

/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelBranch {
  //ветвь с определённым набором параметров
  SquirrelNode startNode;
  SquirrelNode finishNode;
  String startNodeNum="";
  String finishNodeNum="";

  SquirrelCell R=null;
  SquirrelCell L=null;
  SquirrelCell C=null;

  SquirrelCell Z=null;

  SquirrelCell E=null;
  SquirrelCell J=null;

  SquirrelCell addE=null;
  SquirrelCell addJ=null;

  int weight=0;

  boolean JBranch=false;  //Если ветка особеная - только источник тока, то в дальнейшем рассчёте не учитывается
  boolean EBranch=false;  //Если ветка особеная - только источник тока, то в дальнейшем рассчёте не учитывается

  public SquirrelBranch (String paramsList){
    StringTokenizer stringTokenizer = new StringTokenizer(paramsList,SquirrelConstants.Delim);
    while (stringTokenizer.hasMoreTokens()){
      addNewParametr(stringTokenizer.nextToken());
    }
  }

  protected SquirrelBranch() {
  }

  private void addNewParametr(String params){
    //достаём очередной параметр
    StringTokenizer st=new StringTokenizer(params,SquirrelConstants.Delim2);
    String next=st.nextToken();
    if(next.equals(SquirrelConstants.Node)){
      //задаём узел
      if(startNodeNum.equals("")){
        startNodeNum=st.nextToken();
      }else finishNodeNum=st.nextToken();
    }else if(next.equals(SquirrelConstants.R)){
      //задаём активное сопротивление ветви
      R=new SquirrelCell(Double.parseDouble(st.nextToken()),0);
    }else if(next.equals(SquirrelConstants.C)){
      //задаём ёмкость ветви
      C=new SquirrelCell(Double.parseDouble(st.nextToken()),0);
    }else if(next.equals(SquirrelConstants.L)){
      //задаём индуктивность ветви
      L=new SquirrelCell(Double.parseDouble(st.nextToken()),0);
    }else if(next.equals(SquirrelConstants.E)){
      //задаём ЭДС ветви
      E=new SquirrelCell(Double.parseDouble(st.nextToken()),0);
    }else if(next.equals(SquirrelConstants.J)){
      //задаём "Ток" ветви
      J=new SquirrelCell(Double.parseDouble(st.nextToken()),0);
    }
  }
  public void incJ(SquirrelCell J){
    if(addJ==null) addJ=new SquirrelCell();
    addJ.inc(J);
  }
  public void decJ(SquirrelCell J){
    if(addJ==null) addJ=new SquirrelCell();
    addJ.dec(J);
  }
  public void incE(SquirrelCell E){
    if(addE==null) addE=new SquirrelCell();
    addE.inc(E);
  }
  public void decE(SquirrelCell E){
    if(addE==null) addE=new SquirrelCell();
    addE.dec(E);
  }
  private void isJBranch(){
    //true - если в ветви только источник тока
    boolean ind=false;
    if(J!=null)
      if(!J.isEmpty())ind=true;;
    if(R!=null)
      if(!R.isEmpty())ind=false;
    if(C!=null)
      if(!C.isEmpty())ind=false;
    if(L!=null)
      if(!L.isEmpty())ind=false;
    if(E!=null)
      if(!E.isEmpty())ind=false;

    if(ind) JBranch=true;
  }
  private void isEBranch(){
    //true - если в ветви только источник напряжения, либо она вообще пустая
    boolean ind=true;
    //if(E!=null)
    //  if(!E.isEmpty())ind=true;
    if(R!=null)
      if(!R.isEmpty())ind=false;
    if(C!=null)
      if(!C.isEmpty())ind=false;
    if(L!=null)
      if(!L.isEmpty())ind=false;
    if(E!=null)
      if(!E.isEmpty())ind=false;

    if(ind) EBranch=true;
  }
  public void analyze(double W){
    double Re=0;
    double Im=0;
    if(R!=null)Re+=R.getRe();
    if(L!=null)Im+=W*L.getRe();
    if(C!=null)Im+=-1/(W*C.getRe());
    Z=new SquirrelCell(Re,Im);
    isEBranch();
    isJBranch();
  }

  public void print(){
    if(startNode!=null)System.out.print(" S="+startNodeNum+" ;");
    if(finishNode!=null)System.out.print(" F="+finishNodeNum+" ;");

    if(R!=null){
      System.out.print(" R=");
      R.print();
      System.out.print(" ;");
    }

    if(L!=null){
      System.out.print(" L=");
      L.print();
      System.out.print(" ;");
    }

    if(C!=null){
      System.out.print(" C=");
      C.print();
      System.out.print(" ;");
    }

    if(J!=null){
      System.out.print(" J=");
      J.print();

      if(addJ!=null){
        System.out.print(" + ( ");
        addJ.print();

        System.out.print(" )");
      }

      System.out.print(" ;");
    }

    if(E!=null){
      System.out.print(" E=");
      E.print();

      if(addE!=null){
        System.out.print(" + ( ");
        addE.print();

        System.out.print(" )");
      }

      System.out.print(" ;");
    }

    if(Z!=null){
      System.out.print(" Z=");
      Z.print();
      System.out.print(" ;");
    }


  }

  public void println(){
    print();
    System.out.println();
  }

}
