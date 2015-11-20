$( document ).ready( function() {
function Month(data) {
    this.id = ko.observable(data.id);
    this.mes = ko.observable(data.mes);  
}
function Day(data) {
    this.day = ko.observable(data.day);
    this.id  = ko.observable(data.id);
}
function Year(data) {
    this.year = ko.observable(data.year);
}

function Activity(data) {
    this.activity = ko.observable(data.activity);
}
function NacimientoModel() {
    var self = this;
    self.months = ko.observableArray([]);
    self.days = ko.observableArray([]);
    self.years = ko.observableArray([]);
    self.activities = ko.observableArray([]);
    
    var m = ["ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO",
            "JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"];
    var act = ["ESTUDIANTE","INGENIERO","PROFESIONISTA"];
    
    for(i=0; i<act.length;i++){
       self.activities.push(new Activity({ activity: act[i] }));
    }
    
    for(i=0; i<m.length;i++){
        if(i<9){
            self.months.push(new Month({ id: '0'+(i+1), mes:m[i] }));
        }else{
            self.months.push(new Month({ id: i+1, mes:m[i] }));
        }
    }
    for(i=1; i<32;i++)
        if(i<10){
            self.days.push(new Day({id: '0'+i,day : i}));
        }else{
            self.days.push(new Day({id: i,day : i}));
        }
    for(i=1900; i<2006;i++)
        self.years.push(new Year({year: i}));
}
ko.applyBindings(new NacimientoModel());
} );