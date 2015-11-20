$( document ).ready( function() {
function Task(data) {
    this.title = ko.observable(data.title);
}
function UserModel(email,nombre) {
    var self = this;
    self.nombre = ko.observable(nombre);
    self.email = ko.observable(email);
    self.tasks = ko.observableArray([]);

    this.recuperar = function() {
        var mail =  this.email();
        self.tasks.push(new Task({ title: mail }));
        
        $.ajax({
            type: 'POST',
            url: '/god-web/localizar/email',
            data: ko.toJSON({ email: mail }),
            contentType: "application/json",
            dataType: "json",
                success: function (data) {
                   self.nombre(data.mensaje);
            }
	    });        
    };
}
ko.applyBindings(new UserModel("example@gmail.com",""));
} );

/*
    <div style="text-align: center">
    <form id="localiza">
    <p>INGRESA TU EMAIL: <input id="email" data-bind="value: email"/></p>
    <p>NOMBRE DEVUELTO:  <input data-bind="value: nombre" disabled/></p>
    
    
    
    <p>Datos Agregados: <select data-bind="foreach: tasks, visible: tasks().length > 0">
        <option data-bind="value: title,text:title"></option>
        </select></p>
    
    
    <button data-bind="click: recuperar">CONSULTAR EMAIL</button>
    </form>
</div>
 */