import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelReader {
  public SquirrelDatabase readDataBase(String wayPlusName) throws Exception {
    SquirrelDatabase database = new SquirrelDatabase();

    BufferedReader BR = new BufferedReader(new FileReader(wayPlusName));

    String next = BR.readLine();
    StringTokenizer stringTokenizer = new StringTokenizer(next,SquirrelConstants.Delim3);  //убираем комментарии
    // тут общая информация, строку выкидываем

    next = BR.readLine();
    stringTokenizer = new StringTokenizer(next,SquirrelConstants.Delim3);  //убираем комментарии
    readSecondString(stringTokenizer.nextToken(),database);
    //тут строка с частотой и начальным углом

    //коряво описано, возможна оптимизация
    next = BR.readLine();
    if(next==null)throw new Exception("Файл с описанием пуст");
    int numOfString=0;  //"пользовательский" номер строки
    while (!next.equals("")){
      stringTokenizer = new StringTokenizer(next,SquirrelConstants.Delim3);  //убираем комментарии
      numOfString++;
      readOtherString(stringTokenizer.nextToken(),database,numOfString);
      next = BR.readLine();
      if(next==null) break;
    }


    BR.close();

    database.analyze();
    return database;
  }
  private void readSecondString(String params,SquirrelDatabase database){
    StringTokenizer stringTokenizer = new StringTokenizer(params,SquirrelConstants.Delim);
    StringTokenizer st=new StringTokenizer(stringTokenizer.nextToken(),SquirrelConstants.Delim2);
    st.nextToken();//Frequency
    database.setFrequency(Double.parseDouble(st.nextToken()));  //значение

    st=new StringTokenizer(stringTokenizer.nextToken(),SquirrelConstants.Delim2);
    st.nextToken();//Angle
    database.setAngle(Double.parseDouble(st.nextToken()));  //значение
  }

  private void readOtherString(String params,SquirrelDatabase database,int numOfBranch){
    database.addBranch(numOfBranch,new SquirrelBranch(params));
  }
}
