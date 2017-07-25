# Audios
|Campo         |Definición                 |Obligatorio(SI/NO)                     |Validación                                 |Ejemplo                                                               |
|:----------------|:--------------------|:-------------------------------|:-------------------------------------|:---------------------------------------------------------------------|
|user_id|		usuario que esta haciendo uso del sitio (se utiliza para tokenizacion)|		Condicional|		Sin validacion|		user_id: "marcos"|
|site_transaction_id|		nro de operacion|		SI|		Alfanumerico de hasta 39 caracteres|		site_transaction_id: "prueba 1"|
|site_id|		Site relacionado a otro site, este mismo no requiere del uso de la apikey ya que para el pago se utiliza la apikey del site al que se encuentra asociado.|		NO|		Se debe encontrar configurado en la tabla site_merchant como merchant_id del site_id|		site_id: "28464385"|
|token|		token generado en el primer paso|		SI|		Alfanumerico de hasta 36 caracteres. No se podra ingresar un token utilizado para un  pago generado anteriormente.|		token: ""|
|payment_method_id|		id del medio de pago|		SI|		El id debe coincidir con el medio de pago de tarjeta ingresada.|		payment_method_id: 1|
|bin|		primeros 6 numeros de la tarjeta|		SI|		Se valida que sean los primeros 6 digitos de la tarjeta ingresada al generar el token. |		bin: "456578"|
|amount|		importe del pago|		SI|		Importe minimo = 1 ($0.01)</br> Importe Maximo = 9223372036854775807 ($92233720368547758.07)|		amount: 20000|
|currency|		moneda|		SI|		Valor permitido: ARS|		currency: "ARS"|
|installments|		cuotas del pago|		SI|		Valor minimo = 1</br>Valor maximo = 99|		installments: 1|
|payment_type|		forma de pago|		SI|		Valor permitido: single / distributed|		payment_type: "single"|
|establishment_name|		nombre de comercio|		Condicional|		Alfanumerico de hasta 25 caracteres|		establishment_name : "prueba desa soft"|
