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

  int weight=0;

  public SquirrelBranch (String paramsList){
    StringTokenizer stringTokenizer = new StringTokenizer(paramsList,SquirrelConstants.Delim);
    while (stringTokenizer.hasMoreTokens()){
      addNewParametr(stringTokenizer.nextToken());
    }
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
}
