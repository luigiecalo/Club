$(document).ready(function ($) {
    var nav = $('#barra');
    var height = $("#navpricipal").height();
    if (nav.length != 0) {
        var navHomeY = nav.offset().top - height;
        var isFixed = false;
        var $w = $(window);
        $(document).scroll(function () {

            var scrollTop = $w.scrollTop();
            var shouldBeFixed = scrollTop > navHomeY;
            if (shouldBeFixed) {
                if (!isFixed) {
                    nav.css({
                        position: 'fixed',
                        top: height,
                        width:$('#barra').width(),
                    });

                    isFixed = true;
                }
            } else if (isFixed)
            {
                nav.css({
                    position: 'static'
                });
                isFixed = false;
            }

        });
    }
    $('.ir-arriba').click(function () {
        $('body, html').animate({
            scrollTop: '0px'
        }, 300);
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('.ir-arriba').slideDown(300);
        } else {
            $('.ir-arriba').slideUp(300);
        }
    });


});