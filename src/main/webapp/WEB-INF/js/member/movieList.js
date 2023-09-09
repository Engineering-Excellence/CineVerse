  $(".love").click(function () {
      $(this).find('.heart').toggleClass('love');

      $(this).find('.line, .heart').addClass("active").delay(300).queue(function (next) {
          $(this).removeClass("active");
          next();
      });
  });
  
  
var animateButton = function(e) {

  e.preventDefault;
  //reset animation
  e.target.classList.remove('animate');
  
  e.target.classList.add('animate');
  setTimeout(function(){
    e.target.classList.remove('animate');
  },700);
};

var bubblyButtons = document.getElementsByClassName("bubbly-button");

for (var i = 0; i < bubblyButtons.length; i++) {
  bubblyButtons[i].addEventListener('click', animateButton, false);
}
