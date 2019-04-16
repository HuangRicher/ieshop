package com.seamwhole.except;

/**
 * 余额不足异常
 */
public class CheckException extends RuntimeException{

    private Integer errorCode;
    public CheckException(){
        super();
    }

    public CheckException(String mes){
        super( mes );
    }
    public CheckException(String mes,Integer errorCode){
        super( mes );
        this.errorCode=errorCode;
    }

    public CheckException(String mes, Throwable cause){
        super( mes , cause);
    }


    public CheckException(Throwable cause){
        super(  cause);
    }

    public CheckException(String mes, Throwable cause, boolean str, boolean str1){
        super(mes , cause , str ,str1) ;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
