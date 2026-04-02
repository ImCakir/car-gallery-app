package com.caglacakir.exception;

public class BaseException  extends RuntimeException {
    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.prepareErrorMessage());

    }
}


/*
*  Dışarıdan bir BaseException geliyor.baseExceptiondan bir nesne türetildiğinde  Error message nesnesi gelir. Error
* message nesnesınınde ofStatic ve messageType ını doldurması gerekıyor.super ust sınıfı temsıl eder.RuntımeException
* errorMessage ın prepareErrorMessage ını çağırıcaz . Orada setlenmiş olan 2 değer toplanıp birleştirilir  bir mesaj doner.
*  Dönen değeri de super metoduna geçıyoruz.O da runtımeExceptıon a aktarır.
* */