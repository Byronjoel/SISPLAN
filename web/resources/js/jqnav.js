$(document).ready(function () {/*funcion que permite agrgar y quitar la clase active del menu desplegable para jugar con CSS*/
    var cambio = false;
    $('#sidebar > ul > li > a').each(function (index) {
        if (this.href.trim() == window.location) {
            $(this).addClass("active");
            cambio = true;
        }
    });
    
    $('#sidebar > ul > li > ul.sub > li > a').each(function (index) {
        if (this.href.trim() == window.location) {            
            $(this).parent().addClass("active");
            $(this).parent().parent().parent().children().addClass("active");
            cambio = true;
        }
    });
    
    

    if (!cambio) {
        $('.nav li:first').addClass("active");
    }
});