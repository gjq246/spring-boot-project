/*(function ($) {
    $(function () {
        $('.table-expandable').each(function () {
            var table = $(this);
            table.children('thead').children('tr').append('<th></th>');
            table.children('tbody').children('tr').filter(':odd').hide();
            table.children('tbody').children('tr').filter(':even').click(function () {
                var element = $(this);
                element.next('tr').toggle('slow');
                element.find(".table-expandable-arrow").toggleClass("up");
            });
            table.children('tbody').children('tr').filter(':even').each(function () {
                var element = $(this);
                element.append('<td><div class="table-expandable-arrow"></div></td>');
            });
        });
    });
    
})(jQuery); */
function tableexpandableinit(){
	$('.table-expandable').each(function () {
        var table = $(this);
        table.children('thead').children('tr').append('<th></th>');
        table.children('tbody').children('tr').filter(':odd').hide();
        table.children('tbody').children('tr').filter(':even').click(function () {
            var element = $(this);
            element.next('tr').toggle('slow');
            element.find(".table-expandable-arrow").toggleClass("up");
        });
        table.children('tbody').children('tr').filter(':even').each(function () {
            var element = $(this);
            element.append('<td><div class="table-expandable-arrow"></div></td>');
        });
    });
}
function tableexpandableinit2(){
	$('.table-expandable2').each(function () {
        var table = $(this);
        table.children('thead').children('tr').append('<th></th>');
        table.children('tbody').children('tr').filter(':odd').hide();
        table.children('tbody').children('tr').filter(':even').click(function () {
            var element = $(this);
            element.next('tr').toggle('slow');
            element.find(".table-expandable-arrow").toggleClass("up");
        });
        table.children('tbody').children('tr').filter(':even').each(function () {
            var element = $(this);
            element.append('<td><div class="table-expandable-arrow"></div></td>');
        });
    });
}