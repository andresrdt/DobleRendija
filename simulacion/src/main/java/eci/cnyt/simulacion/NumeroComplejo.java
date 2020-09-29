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
public class NumeroComplejo {
    private double realPart;
    private double imaginaryPart;

   
    public NumeroComplejo(double realPart, double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
        this.realPart = realPart;
    }

    public NumeroComplejo(NumeroComplejo a) {
        this.imaginaryPart = a.getImaginaryPart();
        this.realPart = a.getRealPart();
    }

    
    public void conjugate() {
        if (imaginaryPart != 0) {
            imaginaryPart = imaginaryPart * (-1);
        }
    }

    
    public double norm(){
        return Math.sqrt(Math.pow(realPart,2)+Math.pow(imaginaryPart,2));
    }
    
    public double getModulus() {
        return Math.sqrt(Math.pow(realPart, 2) + Math.pow(imaginaryPart, 2));
    }

    
    public double getRealPart() {
        return realPart;
    }

    
    public double getPhase() {
        return Math.atan(imaginaryPart / realPart);
    }

    public double[] getAsPolar() {
        double[] a = new double[2];
        a[0] = getModulus();
        a[1] = getPhase();
        return a;
    }

    
    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    
    public double getImaginaryPart() {
        return imaginaryPart;
    }

    
    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    
    public void inverse() {
        if (realPart != 0) {
            realPart = -realPart;
        }
        if (imaginaryPart != 0) {
            imaginaryPart = -imaginaryPart;
        }
    }

    @Override
    public String toString() {
        if (imaginaryPart > 0) {
            return
                    realPart +
                            " +" + imaginaryPart +
                            'i';
        }else if(imaginaryPart==0){
            return
                    realPart +"";
        }else{
            return
                    realPart +
                            " " + imaginaryPart +
                            'i';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumeroComplejo)) return false;
        NumeroComplejo that = (NumeroComplejo) o;
        return Double.compare(that.realPart, realPart) == 0 &&
                Double.compare(that.imaginaryPart, imaginaryPart) == 0;
    }

}
