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
public class mathCompleja {
    public static NumeroComplejo complexAdd(NumeroComplejo a, NumeroComplejo b){
        return new NumeroComplejo(a.getRealPart()+b.getRealPart(),a.getImaginaryPart()+b.getImaginaryPart());
    }

    public static NumeroComplejo complexSubstraction(NumeroComplejo a, NumeroComplejo b){
        return new NumeroComplejo(a.getRealPart()-b.getRealPart(),a.getImaginaryPart()-b.getImaginaryPart());
    }

    public static NumeroComplejo complexMultiplication(NumeroComplejo a, NumeroComplejo b){
        return new NumeroComplejo((a.getRealPart()*b.getRealPart())-(a.getImaginaryPart()*b.getImaginaryPart()),((a.getRealPart()*b.getImaginaryPart())+(b.getRealPart()*a.getImaginaryPart())));
    }

    public static NumeroComplejo complexDivision(NumeroComplejo a, NumeroComplejo b){
        return new NumeroComplejo(((a.getRealPart()*b.getRealPart())+(a.getImaginaryPart()*b.getImaginaryPart()))/(Math.pow(b.getImaginaryPart(),2)+Math.pow(b.getRealPart(),2)),((b.getRealPart()*a.getImaginaryPart())-(a.getRealPart()*b.getImaginaryPart()))/(Math.pow(b.getRealPart(),2)+Math.pow(b.getImaginaryPart(),2)));
    }
    public static MatrizCompleja matrixAddition(MatrizCompleja a,MatrizCompleja b){
        if(a.rowLength()==b.rowLength() && a.columnLength()==b.columnLength()){
            MatrizCompleja response=new MatrizCompleja(a.rowLength(),a.columnLength());
            for (int row = 0; row<response.rowLength(); row++){
                for(int column = 0; column<response.columnLength(); column++){
                    response.set(row,column,complexAdd(a.get(row,column),b.get(row,column)));
                }
            }
            return response;
        }else{
        return new MatrizCompleja(a.rowLength(),a.columnLength());}
    }

    public static MatrizCompleja matrixSubstraction(MatrizCompleja a,MatrizCompleja b)  {
        if(a.rowLength()==b.rowLength() && a.columnLength()==b.columnLength()){
            MatrizCompleja response=new MatrizCompleja(a.rowLength(),a.columnLength());
            for (int row = 0; row<response.rowLength(); row++){
                for(int column = 0; column<response.columnLength(); column++){
                    response.set(row,column,complexSubstraction(a.get(row,column),b.get(row,column)));
                }
            }
            return response;
        }else{
        return new MatrizCompleja(a.rowLength(),a.columnLength());}
    }

    
    public static MatrizCompleja matrixScalarMultiplication(MatrizCompleja a, NumeroComplejo b){
        MatrizCompleja response=new MatrizCompleja(a.rowLength(),a.columnLength());
        for (int row = 0; row<a.rowLength(); row++){
            for(int column = 0; column<a.columnLength(); column++){
                response.set(row,column,complexMultiplication(a.get(row,column),b));
            }
        }
        return response;
    }

    
    public static MatrizCompleja matrixMultiplication(MatrizCompleja a,MatrizCompleja b)  {
        if(a.columnLength()==b.rowLength()){
           MatrizCompleja answer=new MatrizCompleja(a.rowLength(),b.columnLength());
            for (int row = 0; row<answer.rowLength(); row++){
                for(int column = 0; column<answer.columnLength(); column++){
                    answer.set(row,column,matrixByIndexMultiplication(a,b,row,column));
                }
            }
            return answer;
        }else{
            return new MatrizCompleja(a.rowLength(),b.columnLength());
        }
        
    }

    
    private static NumeroComplejo matrixByIndexMultiplication(MatrizCompleja a,MatrizCompleja b, int r, int c){
        NumeroComplejo answer=new NumeroComplejo(0,0);
        for (int i = 0; i<a.columnLength(); i++){
                 answer=complexAdd(answer,complexMultiplication(a.get(r,i),b.get(i,c)));
        }
        return answer;
    }

   
    public static  MatrizCompleja action(MatrizCompleja a,MatrizCompleja b)  {
        if(a.columnLength()!=a.rowLength()){
           System.out.println("error");
           return null;
        }else if(a.rowLength()!=b.rowLength() || !b.isVector()){
           System.out.println("error");
           return null;
        }else{
            return matrixMultiplication(a,b);
        }
    }

   
    public static NumeroComplejo innerProduct(MatrizCompleja a,MatrizCompleja b) {
        if(a.rowLength()!=b.rowLength()|| !a.isVector() || !b.isVector()){
           System.out.println("error");
           return null;
        }else{
            a.transpose();
            return matrixMultiplication(a,b).get(0,0);
        }
    }

  
    public static MatrizCompleja tensorProduct(MatrizCompleja a, MatrizCompleja b){
        MatrizCompleja answer= new MatrizCompleja(a.rowLength()*b.rowLength(),a.columnLength()*b.columnLength());
        for (int row1 = 0; row1 < a.rowLength(); row1++) {
            for (int column1 = 0; column1 < a.columnLength(); column1++) {
                for (int row2 = 0; row2 < b.rowLength(); row2++) {
                    for (int column2 = 0; column2 < b.columnLength(); column2++) {
                        answer.set(row1*b.rowLength()+row2,column1*b.columnLength()+column2,mathCompleja.complexMultiplication(a.get(row1,column1),b.get(row2,column2)));
                    }
                }
            }
        }
        return answer;
    }

    public static MatrizCompleja generateIdentityMatrix(int size){
        MatrizCompleja identityMatrix=new MatrizCompleja(size,size);
        for (int row = 0; row<identityMatrix.rowLength(); row++){
            for(int column = 0; column<identityMatrix.columnLength(); column++){
                if(row==column){
                    identityMatrix.set(row,column,new NumeroComplejo(1,0));
                }else{
                    identityMatrix.set(row,column,new NumeroComplejo(1,0));
                }

            }
        }
        return identityMatrix;
    }
}
