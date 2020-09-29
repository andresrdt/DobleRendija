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
public class OperacionesCuanticas {
 
    public static double complexAmplitudeProbabilityObservation(MatrizCompleja ket, int position)  {
        if(ket.isVector()){
            return ((ket.get(position,0).norm())/Math.pow(ket.norm(),2));
        }else{
          return 0;
        }
    }

  
    public static NumeroComplejo amplitudeOfTransition(MatrizCompleja a, MatrizCompleja b) {
        if(a.isVector() && b.isVector()){
            return mathCompleja.complexDivision(mathCompleja.innerProduct(a,b),new NumeroComplejo(a.norm()*b.norm(),0));
        }else{
            return new NumeroComplejo(0,0);
        }
    }

    public static NumeroComplejo meanValue(MatrizCompleja observable, MatrizCompleja ket)  {
        if(!ket.isVector()){
            
        }
        if(!observable.isHermitian()){
            
        }
        MatrizCompleja action= mathCompleja.action(observable,ket);
        action.conjugate();
        return mathCompleja.innerProduct(action,ket);
    }


  
    public static NumeroComplejo variance(MatrizCompleja observable, MatrizCompleja ket) {
        NumeroComplejo meanValue= meanValue(observable,ket);
        MatrizCompleja identityMatrix= mathCompleja.generateIdentityMatrix(observable.columnLength());
        MatrizCompleja res=mathCompleja.matrixSubstraction(observable,mathCompleja.matrixScalarMultiplication(identityMatrix,meanValue));;
        res=mathCompleja.matrixMultiplication(res,res);

        return meanValue;
    }
}
