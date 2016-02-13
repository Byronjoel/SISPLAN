function handleSubmit(args, dialog, id) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        $(jqDialog).modal('hide');
    }
    if ($('#' + id).attr("aria-selected") === "false") {
        $('#' + id).addClass("ui-state-focus").attr("aria-selected", "true");
    }
    $('#myDataTables').dataTable();
}

//funcion que actua cuando presiona el link de ingresar nuevo registro
function handleSubmit1(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } 

    $('#myDataTables').dataTable();
}

function handleSubmitMyData(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } 
}

/*      funcion para aplicar estilo a una fila de la tabla  
 
        <script>
            $('tr').hover(function () {
                if ($(this).attr("aria-selected") === "false") {
                    $(this).addClass("ui-state-highlight").attr("aria-selected", "true");
                }
            }, function () {
                $(this).removeClass("ui-state-highlight").attr("aria-selected", "false");
            });
        </script>
 */