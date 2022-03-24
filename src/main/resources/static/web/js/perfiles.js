$(document).on('click', '#bot1', function(){
    document.getElementById("detalle_perfil").innerHTML = "";
    $('#detalle_perfil').append('<div class="card card-body" id = "perfil1">Inversionista que valora la seguridad, con poca tolerancia al riesgo y a perder parte del capital invertido. Puede necesitar liquidez en el corto plazo, por lo que prefiere destinar una participación minoritaria de su inversión a instrumentos de mayor volatilidad. No se recomienda para este perfil invertir en instrumentos tales como productos derivados con fines especulativos o notas estructura.</div>');

});

$(document).on('click', '#bot2', function(){
    document.getElementById("detalle_perfil").innerHTML = "";
    $('#detalle_perfil').append('<div class="card card-body" id = "perfil2"> Inversionista con disposición a incrementar moderadamente el riesgo de su inversión y a eventualmente perder parte del capital invertido en busca de mayor rentabilidad. Tolera una participación balanceada entre instrumentos de alta y baja volatilidad. No se recomienda para este perfil invertir en productos derivados con fines especulativos.</div>');

});

$(document).on('click', '#bot3', function(){
    document.getElementById("detalle_perfil").innerHTML = "";
    $('#detalle_perfil').append('<div class="card card-body">Tolera algunas variaciones en sus inversiones, equilibrando la proporción de instrumentos de mayor riesgo como las acciones nacionales con instrumentos de menor riesgo como depósitos a plazo y fondos mutuos balanceados. No se recomiendan instrumentos de mayor volatilidad como forwards de moneda, opciones, simultáneas o derivados extranjeros.</div>');

});

$(document).on('click', '#bot4', function(){
    document.getElementById("detalle_perfil").innerHTML = "";
    $('#detalle_perfil').append('<div class="card card-body">Inversionista que busca un crecimiento relevante de su inversión, por lo que tiene alta tolerancia a una pérdida de capital en el corto y mediano plazo. Está dispuesto a aceptar una participación mayoritaria de instrumentos de alta volatilidad en su inversión con el fin de lograr sus objetivos de rentabilidad. No se recomienda invertir en productos derivados en el exterior con fines especulativos.</div>');

});


$(document).on('click', '#bot5', function(){
    document.getElementById("detalle_perfil").innerHTML = "";
    $('#detalle_perfil').append('<div class="card card-body"> Busca un elevado retorno en sus inversiones, por lo que está dispuesto a invertir en instrumentos de mayor riesgo como las acciones y los fondos mutuos accionarios. Puede tolerar pérdidas, entendiéndolas en muchas ocasiones como una oportunidad de aumentar sus ganancias. No se recomiendan instrumentos como los derivados extranjeros.</div>');

});

$(document).on('click', '#bot6', function(){
    document.getElementById("detalle_perfil").innerHTML = "";
    $('#detalle_perfil').append('<div class="card card-body">El inversionista con perfil especulativo está enfocado en maximizar su inversión, comprendiendo la volatilidad del mercado y soportando periodos largos de rentabilidad negativa.</div>');

});