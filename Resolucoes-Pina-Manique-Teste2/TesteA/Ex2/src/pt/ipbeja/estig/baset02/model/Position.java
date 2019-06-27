package pt.ipbeja.estig.baset02.model;

import java.util.Objects;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Position
{
   private final int line, col;

   private int value;

   public Position(int line, int col)
   {
      this.line = line;
      this.col = col;

      // this.value = (line + col) * 10; // podemos definir o valor aquando da instanciação porque temos toda a info que precisamos
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */

   @Override
   public String toString()
   {
      return "(" + line + ", " + col + ")";
   }
   /**
    * @return the line
    */
   public int getLine()
   {
      return this.line;
   }

   /**
    * @return the col
    */
   public int getCol()
   {
      return this.col;
   }

   /**
    * Checks if position is inside the board
    * @return true if inside, false otherwise
    */
   public boolean isInside()
   {
      return Position.isInside(this.getLine(), this.getCol());
   }

   /**
    * Checks if line col are inside tha board
    * @param line
    * @param col
    * @return true if inside, false otherwise
    */
   public static boolean isInside(int line, int col)
   {
      return 0 <= line && line < Model.N_LINES &&
             0 <= col && col < Model.N_COLS;
   }

   /* automatically generated in eclipse,
    * with Source->Generate hashCode() and equals()
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */

   public int getValue() {
       // podiamos também recalcular e devolver (line + col) * 10 sempre que getValue é invocado (mas como esta class não tem setters, não há perigo de os valores de line e col mudarem)
      return value;
   }

   public void setValue(int value) {
      this.value = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Position position = (Position) o;
      return line == position.line &&
              col == position.col;
   }

   @Override
   public int hashCode() {
      return Objects.hash(line, col);
   }
}
