/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelMatrixOperations {
  public SquirrelMatrix SquirrelMatrixSum(SquirrelMatrix ferst, SquirrelMatrix second) throws Exception {
    // поэлементное сложение матриц
    SquirrelMatrix resultMatrix=null;

    //проверка матриц на совпадение
    if (ferst.getLength()!=second.getLength())throw new Exception("Матрицы не совпадают по длине");
    if (ferst.getWidth()!=second.getWidth())throw new Exception("Матрицы не совпадают по ширине");

    resultMatrix = new SquirrelMatrix(ferst.getLength(),ferst.getWidth());

    for (int i=0;i<ferst.getLength();i++){
      for (int j=0;j<ferst.getWidth();j++){
        resultMatrix.setElem(i,j, new SquirrelCell().inc(ferst.getElem(i,j),second.getElem(i,j)));
      }
    }

    return resultMatrix;
  }

  public SquirrelMatrix SquirrelMatrixDif(SquirrelMatrix ferst, SquirrelMatrix second) throws Exception {
    // поэлементное сложение матриц
    SquirrelMatrix resultMatrix=null;

    //проверка матриц на совпадение
    if (ferst.getLength()!=second.getLength())throw new Exception("Матрицы не совпадают по длине");
    if (ferst.getWidth()!=second.getWidth())throw new Exception("Матрицы не совпадают по ширине");

    resultMatrix = new SquirrelMatrix(ferst.getLength(),ferst.getWidth());

    for (int i=0;i<ferst.getLength();i++){
      for (int j=0;j<ferst.getWidth();j++){
        resultMatrix.setElem(i,j, new SquirrelCell().dec(ferst.getElem(i,j),second.getElem(i,j)));
      }
    }

    return resultMatrix;
  }

}
