/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelMatrix {
  protected SquirrelCell[][] array;

  private int length=0;
  private int width=0;

  public SquirrelMatrix(int length, int width) throws Exception {
    if ((length<0)|(width<0))throw new Exception("Попытка создать массив отрицательной длины");
    array = new SquirrelCell[length][width];

    this.length=length;
    this.width=width;

  }

  public SquirrelCell getElem(int pos1, int pos2) throws Exception {
    if ((pos1<0)|(pos1>=array.length)|(pos2<0)|(pos2>=array[pos1].length))throw new Exception("Искомый элемент выходит за пределы массива");
    return array[pos1][pos2];
  }
  public void setElem(int pos1, int pos2, SquirrelCell newCell) throws Exception {
    if ((pos1<0)|(pos1>=array.length)|(pos2<0)|(pos2>=array[pos1].length))throw new Exception("Искомый элемент выходит за пределы массива");
    array[pos1][pos2]=newCell;
  }

  public void print(){
    try{
      for (int i=0;i<array.length;i++){
        for (int j=0;j<array[i].length;j++){
          array[i][j].print();
        }
        System.out.println();
      }
    } catch (Exception e){
      System.out.println("Ошибка при вывод матрицы");
    }
  }

  public void println(){
    print();
    System.out.println();
  }

  //нужно доделать исключения
  public int getLength(){
    return length;
  }
  public int getWidth(){
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setLength(int length) {
    this.length = length;
  }

}
