/**
 * Created by Vlad on 06.09.2015.
 */
public class SquirrelCell{

  protected double RePart=0;
  protected double ImPart=0;


  public SquirrelCell (){
  }
  public SquirrelCell (double rePart, double imPart){
    this.RePart=rePart;
    this.ImPart=imPart;
  }
  public double getRe() {
    return RePart;
  }
  public void setRe(double rePart) {
    RePart = rePart;
  }
  public double getIm() {
    return ImPart;
  }
  public void setIm(double imPart) {
    ImPart = imPart;
  }


  //учесть пустые элементы

  public SquirrelCell inc(SquirrelCell ferst, SquirrelCell second) {
    RePart=0;
    ImPart=0;
    if(ferst!=null){
      RePart+=ferst.getRe();
      ImPart+=ferst.getIm();
    }
    if(second!=null){
      RePart+=second.getRe();
      ImPart+=second.getIm();
    }
    return this;
  }
  public SquirrelCell dec(SquirrelCell ferst, SquirrelCell second) {
    RePart=0;
    ImPart=0;
    if(ferst!=null){
      RePart+=ferst.getRe();
      ImPart+=ferst.getIm();
    }
    if(second!=null){
      RePart-=second.getRe();
      ImPart-=second.getIm();
    }
    return this;
  }
  public SquirrelCell mult(SquirrelCell ferst, SquirrelCell second) throws Exception {
    RePart=0;
    ImPart=0;

    if((ferst==null)|(second==null)) throw new Exception("Ошибка при произведении - элемент не задан");

    RePart = ferst.getRe()*second.getRe()-ferst.getIm()*second.getIm();
    ImPart = ferst.getIm()*second.getRe()+ferst.getRe()*second.getIm();

    return this;
  }
  public SquirrelCell divn(SquirrelCell ferst, SquirrelCell second) throws Exception {
    RePart=0;
    ImPart=0;
    if((ferst==null)|(second==null)) throw new Exception("Ошибка при делении - элемент не задан");
    if((second.getRe()==0)&(second.getIm()==0)) throw new Exception("Ошибка при делении - второй элемент нулевой");
    double tmp = second.getRe()*second.getRe()+second.getIm()*second.getIm();
    RePart=(ferst.getRe()*second.getRe()+ferst.getIm()*second.getIm())/tmp;
    ImPart=(ferst.getIm()*second.getRe()-ferst.getRe()*second.getIm())/tmp;
    return this;
  }
  public SquirrelCell inc(SquirrelCell second) {
    if(second!=null){
      RePart+=second.getRe();
      ImPart+=second.getIm();
    }
    return this;
  }
  public SquirrelCell dec(SquirrelCell second) {
    if(second!=null){
      RePart-=second.getRe();
      ImPart-=second.getIm();
    }
    return this;
  }
  public SquirrelCell mult(SquirrelCell second) throws Exception {

    if((second==null)) throw new Exception("Ошибка при произведении - второй элемент не задан");

    double tmpRePart = RePart*second.getRe()-ImPart*second.getIm();
    double tmpImPart = ImPart*second.getRe()+RePart*second.getIm();
    this.RePart=tmpRePart;
    this.ImPart=tmpImPart;

    return this;
  }
  public SquirrelCell divn(SquirrelCell second) throws Exception {

    if((second==null)) throw new Exception("Ошибка при делении - второй элемент не задан");
    if((second.getRe()==0)&(second.getIm()==0)) throw new Exception("Ошибка при делении - второй элемент нулевой");
    double tmp = second.getRe()*second.getRe()+second.getIm()*second.getIm();
    double tmpRePart=(RePart*second.getRe()+ImPart*second.getIm())/tmp;
    double tmpImPart=(ImPart*second.getRe()-RePart*second.getIm())/tmp;

    this.RePart=tmpRePart;
    this.ImPart=tmpImPart;

    return this;
  }
  public SquirrelCell addMultiplication(SquirrelCell ferst, SquirrelCell second) throws Exception {
    //Прибавляет к текущему значению произведение двух элементов
    if((ferst!=null)&(second!=null)) {
      RePart += ferst.getRe() * second.getRe() - ferst.getIm() * second.getIm();
      ImPart += ferst.getIm() * second.getRe() + ferst.getRe() * second.getIm();
    }//в противном случчае - один из элементов пустой, произведение равно нулю
    return this;
  }

  //Если все параметры нулевые - возвращает true
  public boolean isEmpty(){
    if((RePart==0&(ImPart==0)))return false;
    return true;
  }

  public void print() {
    System.out.print(" " + RePart);
    if (ImPart>0)System.out.print(" + j*"+ImPart);//+" ;"
    else if (ImPart<0) System.out.print(" - j*"+(-ImPart));
  }
  public void println() {
    print();
    System.out.println();
  }
}
