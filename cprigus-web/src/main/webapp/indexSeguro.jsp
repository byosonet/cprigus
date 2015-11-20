<%@ include file="./WEB-INF/views/layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Sólo a Dios la Gloria</title>
  <jsp:include page="./WEB-INF/views/layout/staticResources.jsp"></jsp:include>
  <script type="text/javascript">
      $(function(){ 
          $('select#tipoFamilia').select2();
          $('input#dialCantidad').knob();
          $('input#dialEdad').knob();
          $('input#dialIntegrantes').knob();
          
          $('input#agregar').click(function(){
              var cantidad = $('input#dialCantidad').val();
              var integrantes = $('input#dialIntegrantes').val();
              var select = $('select#tipoFamilia').val();
              var totalCompra = $('u#totalCompra').text();
              var r1 = totalCompra.replace('$','');
              var r2 = r1.replace(',','');
              var totalInt = parseFloat(r2);
              var totIntegrantes = parseInt(integrantes) + parseInt(cantidad);
              
              $('input#dialIntegrantes').val(totIntegrantes);
              var precio = Math.floor((Math.random() * 10000) + 1);
              var imp = parseFloat(totalInt) + precio;
              
              imp = formatoMoneda(imp);
              precio = formatoMoneda(precio);
              
              $('u#totalCompra').text('$ '+imp);
              var x = 'abc'+Math.floor((Math.random() * 10000000000) + 1);
              $('div#listaSeguro').after('<div class="row col-sm-12 sub-header" id="'+x+'">'
                         +'<br>'
                         +'<label id="'+x+'" style="text-align: center;" class="control-label col-sm-2 col-xs-1">'+cantidad+'</label>'
                         +'<span class="control-label col-sm-2 col-xs-1 glyphicon glyphicon-user"></span>'
                         +'<span id="'+x+'" style="text-align: center;" class="control-label col-sm-3 col-xs-2">'+select+'</span>'
                         +'<strong id="'+x+'" style="text-align: right;" class="control-label col-sm-4 col-xs-5">'+'$ '+precio+'</strong>'
                         +'<label id="'+x+'" class="btn btn-danger control-label col-sm-1 col-xs-2" style="margin-top:-10px;border-radius:20px"><strong>-</strong></label>'
                     +'</div>');
             
                var idHtml = 'label#'+x+'.btn.btn-danger';
                $(idHtml).live('click',function(){
                      var id = $(this).attr('id');
                      var idCantidad = 'label#'+id;
                      var idCant = $(idCantidad).text()[0];
                      var idPrecio = 'strong#'+id;
                      var idPre = $(idPrecio).text();
                      var integrantes = $('input#dialIntegrantes').val();
                      var totIntegrantes = parseInt(integrantes) - parseInt(idCant);
                      $('input#dialIntegrantes').val(totIntegrantes);
                      var totalCompra = $('u#totalCompra').text();
                      var r1 = totalCompra.replace('$','');
                      var r2 = r1.replace(',','');
                      var totalInt = parseFloat(r2);
                      var p1 = idPre.replace('$','');
                      var p2 = p1.replace(',','');
                      var precio = parseFloat(p2);
                      var imp = parseFloat(totalInt) - precio;
                      imp = formatoMoneda(imp);
                      $('u#totalCompra').text('$ '+imp);
              
                      var divHtml = 'div#'+id;
                      $(divHtml).remove();
                });
          });
          
          function formatoMoneda(moneda){
              moneda = moneda.toFixed(2).replace(/./g, function(c, i, a) {
                    return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                });
              return moneda;
          }
          
          var slider = document.getElementById('slider');
            noUiSlider.create(slider, {
                    start: [20],
                    connect: 'lower',
                    range: {
                            'min': 0,
                            'max': 100
                    },
                    pips: {
                        mode: 'values',
                        values: [20, 50, 80],
                        density: 12,
                        format: {to: updatePips}
                    }
            });
            
            slider.noUiSlider.on('change', function ( values, handle ) {
                    if ( values[handle] < 50) {
                            $('input#valorBarra').val('familiar');
                            $('strong#tipoSeguro').text('Familiar');
                            $('strong#tipoSeguro').removeClass().addClass('btn btn-sm btn-success');
                            $('input#embarazo').attr('checked',true);
                            $('input#accidente').attr('checked',true);
                            $('input#fallecimiento').attr('checked',false);
                            $('input#enfermedad').attr('checked',false);
                            $('input#asalto').attr('checked',false);
                            $('input#vida').attr('checked',false);
                            $('input#robo').attr('checked',false);
                            $('input#familiar').attr('checked',false);
                            $('input#infarto').attr('checked',false);
                                                   
                    } else if ( values[handle] > 50 && values[handle] < 80) {
                            $('input#valorBarra').val('laboral');
                            $('strong#tipoSeguro').text('Laboral');
                            $('strong#tipoSeguro').removeClass().addClass('btn btn-sm btn-info');
                            $('input#embarazo').attr('checked',false);
                            $('input#accidente').attr('checked',false);
                            $('input#fallecimiento').attr('checked',true);
                            $('input#enfermedad').attr('checked',true);
                            $('input#asalto').attr('checked',true);
                            $('input#vida').attr('checked',false);
                            $('input#robo').attr('checked',false);
                            $('input#familiar').attr('checked',false);
                            $('input#infarto').attr('checked',false);
                    } else if ( values[handle] > 80 ) {
                            $('input#valorBarra').val('personal');
                            $('strong#tipoSeguro').text('Personal');
                            $('strong#tipoSeguro').removeClass().addClass('btn btn-sm btn-danger');
                            $('input#embarazo').attr('checked',false);
                            $('input#accidente').attr('checked',false);
                            $('input#fallecimiento').attr('checked',false);
                            $('input#enfermedad').attr('checked',false);
                            $('input#asalto').attr('checked',false);
                            $('input#vida').attr('checked',true);
                            $('input#robo').attr('checked',true);
                            $('input#familiar').attr('checked',true);
                            $('input#infarto').attr('checked',true);
                    }
            });
            
            function updatePips( value, type ){
                switch(true)   {
                    case (value === 20):
                        value = "<label>Familiar</label>";
                        break;
                    case (value === 80):
                        value = "<label>Personal</label>";
                        break;
                    case (value === 50):
                        value = "<label>Laboral</label>";
                        break;
                }
                return value;
            }
      });
  </script>
  <style>
            
  </style>
</head>
<body>
     <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/god-web/" style="color:white;"></a>
          </div>
          <div style="height: 1px;" aria-expanded="false" class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                  <a style="color:white;" aria-expanded="true" aria-haspopup="true" role="button" data-toggle="dropdown" class="navbar-brand dropdown-toggle" href="#">Menú </a>
                </li>
              </ul>
          </div>
        </div>
      </nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-1 main">
            <h2 style="text-align: left;">Paso 1: Cotizar Seguro</h2>
                <div class="row">
                    <div class="col-sm-6" style="margin-top: 30px;height: 100px">
                        <label class="font-bold">Tipo Seguro</label>
                        <div id="slider" class="noUi-target noUi-ltr noUi-horizontal noUi-background"></div>
                        <input id="valorBarra" value="" type="hidden"/>
                    </div>
                    <br>
                    <div class="col-sm-6">
                       <div>

                               <div class="alert alert-info table-responsive" style="background-color: #f9f9f9">
                                 <table class="table table-striped" id="cobertura">
                                   <thead>
                                    <tr>
                                      <th></th>
                                      <th>Cobertura</th>
                                      <th></th>

                                    </tr>
                                  </thead>
                                   <tbody>
                                        <tr>
                                            <td><div class="checkbox checkbox-info">
                                                <input type="checkbox" value="SI" name="notificar" id="fallecimiento" onclick="return false">
                                                <label>
                                                    Fallecimiento
                                                </label>
                                            </div>  
                                            </td>
                                            <td><div class="checkbox checkbox-danger">
                                                <input type="checkbox" value="SI" name="notificar" id="vida" onclick="return false">
                                                <label>
                                                    Vida
                                                </label>
                                            </div>  
                                            </td>
                                            <td><div class="checkbox checkbox-info">
                                                    <input type="checkbox" value="SI" name="notificar" id="enfermedad" onclick="return false">
                                                    <label>
                                                        Enfermedad
                                                    </label>
                                                </div>  
                                            </td>
                                         </tr>
                                         <tr>
                                            <td><div class="checkbox checkbox-success">
                                                <input type="checkbox" value="SI" checked name="notificar" id="accidente" onclick="return false">
                                                <label>
                                                    Accidente
                                                </label>
                                            </div>  
                                            </td>
                                            <td><div class="checkbox checkbox-info">
                                                <input type="checkbox" value="SI" name="notificar" id="asalto" onclick="return false">
                                                <label>
                                                    Asalto
                                                </label>
                                            </div>  
                                            </td>
                                            <td><div class="checkbox checkbox-danger">
                                                <input type="checkbox" value="SI" name="notificar" id="robo" onclick="return false">
                                                <label>
                                                    Robo
                                                </label>
                                            </div>  
                                            </td>
                                         </tr>
                                         <tr>
                                            <td><div class="checkbox checkbox-danger">
                                                <input type="checkbox" value="SI" name="notificar" id="familiar" onclick="return false">
                                                <label>
                                                    Familiar
                                                </label>
                                            </div>  
                                            </td>
                                            <td><div class="checkbox checkbox-danger">
                                                    <input type="checkbox" value="SI" name="notificar" id="infarto" onclick="return false">
                                                    <label>
                                                        Infarto
                                                    </label>
                                                </div>  
                                            </td>
                                            <td><div class="checkbox checkbox-success">
                                                <input type="checkbox" value="SI" checked name="notificar" id="embarazo" onclick="return false">
                                                <label>
                                                    Embarazo
                                                </label>
                                            </div>  
                                            </td>
                                         </tr>
                                   </tbody>
                                 </table>
                               </div>
                        </div>
                    </div>
                </div>
            
            <div class="row col-md-12 sub-header">
                    <h4 style="text-align: left;">Calculadora de Seguro</h4>
            </div>
            <br>
            
            <div class="row">
                <div class="col-md-6" style="text-align: left;">
                    
                    <div class="row col-sm-12 sub-header">
                     <br>
                     <span class="control-label col-sm-1 col-xs-2 glyphicon glyphicon-tasks" style="font-size: 2em;"></span>
                     <label class="control-label col-sm-11 col-xs-10">Usted está comprando: <br>Seguro de Salud tipo: <label><strong id="tipoSeguro" class="btn btn-sm btn-success">Familiar</strong></label></label>
                    </div>
                    
                     <div class="row col-sm-12 sub-header">
                     <br>
                     <span class="control-label col-sm-1 col-xs-2 glyphicon glyphicon-credit-card" style="font-size: 2em;"></span>
                     <label class="control-label col-sm-11 col-xs-10">Forma de Pago: <br>Pago mensual a través de la Línea de captura.</label>
                    </div>
                    
                    <div class="row col-sm-12 sub-header">
                     <br>
                    <label style="text-align: center;" class="control-label col-sm-4 col-xs-4">Cantidad</label>
                    <label style="text-align: center;" class="control-label col-sm-4 col-xs-3">Edad</label>
                    <label style="text-align: left;" class="control-label col-sm-4 col-xs-5">Parentesco</label>
                    </div>
                    
                    
                    <div class="row col-sm-12 sub-header">
                     <br>
                        <div class="col-sm-4 col-xs-3" style="text-align: center;">
                            <input data-step="1" data-min="1" data-max="10" data-width="50" data-height="50" data-fgColor="#66CC66" id="dialCantidad" type="text" value="1" class="dial" data-angleOffset=90 data-linecap=round>
                        </div>
                        <div class="col-sm-4 col-xs-3" style="text-align: center;">
                            <input data-step="1" data-min="18" data-max="45" data-width="50" data-height="50" data-fgColor="#66CC66" id="dialEdad" type="text" value="18" class="dial" data-angleOffset=90 data-linecap=round>
                        </div>
                        <div class="col-sm-4 col-xs-6" style="text-align: left;">
                            <span class="glyphicon glyphicon-user"></span>
                             <select id="tipoFamilia" class="" style="width: 70px;">
                                <option value="Papa">Papa</option>
                                <option value="Hijo">Hijo</option>
                              </select> 
                        </div>
                    </div>
                    
                     <div class="row col-sm-12">
                         <br>
                         <label style="text-align: center;" class="control-label col-sm-3 col-xs-2"></label>
                         <span class="control-label col-sm-2 col-xs-1"></span>
                         <span style="text-align: center;" class="control-label col-sm-3 col-xs-2"></span>
                         <input class="control-label col-sm-4 col-xs-6 btn btn-primary" id="agregar" type="button" value="AGREGAR"/>
                     </div>
                    
                </div>
                
                <div class="col-md-1"></div>
                
                <div class="col-md-5">
                    
                    <div class="row col-sm-12 sub-header" id="listaSeguro">
                        <br>
                        <label class="control-label col-sm-6 col-xs-6" style="text-align: center;margin-top: 15px;">Integrantes:</label>
                        <div class="col-sm-6 col-xs-6" style="text-align: center;margin-top: 7px;">
                            <input data-step="1" data-min="0" data-max="50" data-width="50" data-height="50" data-fgColor="#66CC66" id="dialIntegrantes" type="text" value="0" class="dial" data-angleOffset=90 data-linecap=round>
                        </div>
                    </div>
                    
                    <div class="row col-sm-12 sub-header">
                         <br>
                         <label style="text-align: center;" class="control-label col-sm-3 col-xs-1"></label>
                         <span class="control-label col-sm-2 col-xs-1"></span>
                         <span style="text-align: center;margin-left: -10px;" class="control-label col-sm-3 col-xs-3"><strong>TOTAL</strong></span>
                         <label style="text-align: left;" class="control-label col-sm-4 col-xs-6"><u id="totalCompra">$ 0.00</u></label>
                     </div>
                    
                     <div class="row col-sm-12">
                         <br>
                         <label style="text-align: center;" class="control-label col-sm-3 col-xs-2"></label>
                         <span class="control-label col-sm-2 col-xs-1"></span>
                         <span style="text-align: center;" class="control-label col-sm-3 col-xs-2"></span>
                         <input class="control-label col-sm-4 col-xs-6 btn btn-primary" type="button" value="CONTRATAR"/>
                     </div>
                    
                </div>
            </div>         
        </div>
    </div>    
</div>
         

<br>
<br>
<br>
<br>
  <nav class="navbar navbar-inverse navbar-fixed-bottom">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="https://www.linkedin.com/pub/gustavo-ulises-trejo-armenta/94/aa6/262" style="font-size: 0.75em;color:white;">by Ulysses M@c</a>
            </div>
          </div>
</nav>
</body>
</html>
