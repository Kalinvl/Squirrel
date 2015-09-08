/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelMatrix {
  protected SquirrelCell[][] array;

  public SquirrelMatrix(int length1, int length2) throws Exception {
    if ((length1<0)|(length2<0))throw new Exception("Попытка создать массив отрицательной длины");
    array = new SquirrelCell[length1][length2];
  }

  public SquirrelCell getElem(int pos1, int pos2) throws Exception {
    if ((pos1<0)|(pos1>=array.length)|(pos2<0)|(pos2>=array[pos1].length))throw new Exception("Искомый элемент выходит за пределы массива");
    return array[pos1][pos2];
  }
  public void setElem(int pos1, int pos2, SquirrelCell newCell) throws Exception {
    if ((pos1<0)|(pos1>=array.length)|(pos2<0)|(pos2>=array[pos1].length))throw new Exception("Искомый элемент выходит за пределы массива");
    array[pos1][pos2]=newCell;
  }

  //нужно доделать исключения
  public int getLength(){
    return array.length;
  }
  public int getWidth(){
    return array[0].length;
  }

  public void print(){
    try{
      for (int j=0;j<array[0].length;j++){
        for (int i=0;i<array.length;i++){
          array[i][j].print();
        }
        System.out.println();
      }
    } catch (Exception e){
      System.out.println("Ошибка при вывод матрицы");
    }
  }
}
