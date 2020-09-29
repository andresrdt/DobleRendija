/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.cnyt.simulacion;

/**
 *
 * @author andy
 */
public class MatrizCompleja {
     private NumeroComplejo[][] matrix;

    
    public MatrizCompleja(int rows, int columns) {
        matrix = new NumeroComplejo[rows][columns];
    }

    
    public MatrizCompleja(NumeroComplejo[][] matrix) {
        this.matrix = matrix;
    }

    public MatrizCompleja(MatrizCompleja a){
        matrix=new NumeroComplejo[a.rowLength()][a.columnLength()];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column] = new NumeroComplejo(a.get(row,column));
            }
        }
    }
    /**
     * Transpose de complex matrix.
     */
    public void transpose() {
        NumeroComplejo[][] t = new NumeroComplejo[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                t[column][row] = matrix[row][column];
            }
        }
        matrix = t;
    }

    
    public void conjugate() {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column].conjugate();
            }
        }
    }


    
    public void adjoint() {
        transpose();
        conjugate();
    }

    
    public NumeroComplejo get(int row, int column) {
        return matrix[row][column];
    }

    public int columnLength() {
        return matrix[0].length;
    }

    public int rowLength() {
        return matrix.length;
    }

    public void set(int row, int column, NumeroComplejo c) {
        matrix[row][column] = c;
    }

    
    public void inverse() {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                matrix[row][column].inverse();
            }
        }
    }

    
    public boolean isVector() {
        if ((rowLength() == 1 || columnLength() == 1) && columnLength() != rowLength()) {
            return true;
        } else {
            return false;
        }
    }

    
    public double norm() {
        if (isVector()) {
            NumeroComplejo norm = new NumeroComplejo(0,0);
            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[row].length; column++) {
                    norm=mathCompleja.complexAdd(norm,new NumeroComplejo(Math.pow(matrix[row][column].getRealPart(),2),Math.pow(matrix[row][column].getImaginaryPart(),2)));
                }
            }
            double result=Math.sqrt(norm.getRealPart()+norm.getImaginaryPart());
            norm.setRealPart(Math.sqrt(norm.getRealPart()));

            return result;
        }else{
            return 0;
        }
    }

    
    public boolean isHermitian(){
        MatrizCompleja a = new MatrizCompleja(this);
        a.adjoint();

        return this.equals(a);
    }

    
    public boolean isUnitary(){
        if(columnLength()!=rowLength()){
            return false;
        }else{
            MatrizCompleja a = new MatrizCompleja(this);
            a.adjoint();
            MatrizCompleja i =mathCompleja.matrixMultiplication(this,a);
            if(i.equals(mathCompleja.matrixMultiplication(a,this))){
                for (int row = 0; row < i.rowLength(); row++) {
                    for (int column = 0; column < i.columnLength(); column++) {
                        if((row==column && !i.get(row, column).equals(new NumeroComplejo(1, 0)))|| (row!=column && !i.get(row, column).equals(new NumeroComplejo(0, 0)))){
                            return false;
                        }
                    }
                }
                return true;
            }else{return false;}
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                str = str + matrix[row][column].toString();
                if (column == matrix[row].length - 1) {
                    str = str + "\n";
                } else {
                    str = str + " , ";
                }
            }
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MatrizCompleja) {
            if(((MatrizCompleja) o).columnLength()!=columnLength() || ((MatrizCompleja) o).rowLength()!=rowLength()){
                return false;
            }
            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[row].length; column++) {
                    if (!matrix[row][column].equals(((MatrizCompleja) o).get(row, column))) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
}
