 //

$('.icono-caracteristicas').on('click', function(){
   var target = $(this).attr('rel');
   $(this).addClass("icono-caracteristicas-h");
   $(".icono-caracteristicas").not(this).removeClass("icono-caracteristicas-h");
   $("#"+target).fadeIn().siblings("div").css("display","none");
});

//


(function($) {
  
  'use strict';

  // variables
  var $isAnimatedSecond = $('.is-animated'),
      $isAnimatedSecondSingle = $('.is-animated__single');

  // initialize fullPage
  $('#fullpage').fullpage({

   	anchors: ['inicio', 'platypus', 'caracteristicas', 'contacto', 'lastPage'],
    menu: '#menu',
    css3: true,
    scrollingSpeed: 1000,
    slidesNavigation: true,
    //navigation: true,
    onLeave: function(index, nextIndex, direction) {
    
      /**
      * use the following condition: 
      *
      *   if( index == 1 && direction == 'down' ) {
      *
      * if you haven't enabled the dot navigation
      * or you aren't interested in the animations that occur 
      * when you jump (using the dot navigation) 
      * from the first section to another sections 
      */
      
      // first animation
      //if( index == 1 && nextIndex == 2 ) { 
        $isAnimatedSecond.addClass('animated bounceInUp'); 
        $isAnimatedSecond.eq(8).css('animation-delay', '.3s');
        $isAnimatedSecond.eq(7).css('animation-delay', '.6s');
        $isAnimatedSecond.eq(6).css('animation-delay', '.9s');
        $isAnimatedSecond.eq(5).css('animation-delay', '.12s');
        $isAnimatedSecond.eq(4).css('animation-delay', '.15s');
        $isAnimatedSecond.eq(3).css('animation-delay', '.18s');
        $isAnimatedSecond.eq(2).css('animation-delay', '.21s');
        $isAnimatedSecond.eq(1).css('animation-delay', '.24s');
        $isAnimatedSecond.eq(0).css('animation-delay', '.27s');
        $isAnimatedSecondSingle.addClass('animated bounceInRight').css('animation-delay', '.3s');
    //  }

      
    }

  });
  
})(jQuery);
