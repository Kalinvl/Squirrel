/**
 * Created by Vlad on 06.09.2015.
 */
public interface SquirrelCellInterface {
  public abstract SquirrelCellInterface inc(SquirrelCellInterface ferst, SquirrelCellInterface second); //сумма двух элементов
  public abstract SquirrelCellInterface dec(SquirrelCellInterface ferst, SquirrelCellInterface second); //разность двух элементов
  public abstract SquirrelCellInterface mult(SquirrelCellInterface ferst, SquirrelCellInterface second); //произведение двух элементов
  public abstract SquirrelCellInterface divn(SquirrelCellInterface ferst, SquirrelCellInterface second);  //частное

  public abstract SquirrelCellInterface inc(SquirrelCellInterface second); //сумма двух элементов
  public abstract SquirrelCellInterface dec(SquirrelCellInterface second); //разность двух элементов
  public abstract SquirrelCellInterface mult(SquirrelCellInterface second); //произведение двух элементов
  public abstract SquirrelCellInterface divn(SquirrelCellInterface second);  //частное

  public abstract void Print();
}
